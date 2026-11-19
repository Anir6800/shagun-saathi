# Data Model

The single source of truth for the Firestore data shape. **Update this file in the same task that changes a collection, field, rule, or index** — never let it drift from the real schema.

This is the live schema, enforced by `firestore.rules` and `firestore.indexes.json` (deployed to the `shagun-saathi` project — step 4.1). Money fields are always integer rupees (never floating point), per `RULES.md`.

## Collections

| Path | Kind | Purpose |
|---|---|---|
| `events/{eventId}` | top-level document | One wedding/family event. Holds the `members` role map. |
| `events/{eventId}/entries/{entryId}` | subcollection | One gift entry per guest contribution (cash, gift, or online/UPI). |
| `events/{eventId}/entries/{entryId}/history/{historyId}` | subcollection | Append-only record of changes to one entry (edits, voids) — never deleted. |
| `events/{eventId}/boxes/{writerId}` | subcollection, doc id = writer's uid | Cash box state for one writer: note stock, float, status. |
| `guests/{guestId}` | top-level document | A guest, scoped per owner, reusable across that owner's events. |

## Fields

### `events/{eventId}`

| Field | Type | Notes |
|---|---|---|
| `name` | string | e.g. "Priya's Wedding". |
| `type` | string | e.g. `wedding`, `engagement`, `other`. |
| `date` | timestamp | |
| `familyName` | string | |
| `ownerId` | string | uid of the event owner. |
| `status` | string | `live` or `ended`. Once `ended`, writers become read-only (see `firestore.rules`). |
| `paymentTarget` | map? | UPI details for online gifts (UPI id and/or uploaded QR image URL). No money ever flows through the app (D-009). |
| `members` | map\<uid, string\> | Role per member: `owner`, `writer`, `helper`, or `viewer`. |

### `events/{eventId}/entries/{entryId}`

| Field | Type | Notes |
|---|---|---|
| `guestId` | string | Reference to `guests/{guestId}`. |
| `guestName` | string | Denormalized at write time, so the entry still reads sensibly even if the guest doc changes later. |
| `type` | string | `cash`, `gift`, or `online`. |
| `netAmount` | int | Integer rupees. For `cash`, notes-in minus change-out. For `gift`/`online`, the recorded value. |
| `notesIn` | map\<string, int\> | Denomination → count handed over by the guest (cash only), e.g. `{"500": 2, "100": 1}`. |
| `changeOut` | map\<string, int\> | Denomination → count given back as change (cash only). |
| `giftDescription` | string? | For `type: gift` — what was given. |
| `photoUrl` | string? | Firebase Storage URL, e.g. a photo of a physical gift or an uploaded UPI QR. |
| `writerId` | string | uid of the writer/helper who recorded the entry. Must equal the creator's own uid (enforced server-side). |
| `serial` | string | Per-writer prefix, e.g. `"W1-007"` — writer's short id + a sequence number, so two writers never collide offline. |
| `createdAt` | timestamp | |
| `confirmed` | bool | For `type: online` — whether the family confirmed the UPI payment arrived. Always `true` for `cash`/`gift`. |
| `voidedAt` | timestamp? | Null/absent if active. Set when voided. |
| `voidedReason` | string? | Required when `voidedAt` is set — the entry itself is never deleted (D-010). |

### `events/{eventId}/entries/{entryId}/history/{historyId}`

| Field | Type | Notes |
|---|---|---|
| `guestId` | string | Denormalized from the parent entry, so a collection-group query can answer "this guest's history" without reading every event. |
| `previousValues` | map | Snapshot of the entry's fields before this edit. |
| `editedBy` | string | uid of who made the change. |
| `editedAt` | timestamp | |

### `events/{eventId}/boxes/{writerId}`

Document id is the writer's own uid.

| Field | Type | Notes |
|---|---|---|
| `stock` | map\<string, int\> | Denomination → note count currently in the box. |
| `float` | int | Integer rupees kept aside as starting change float. |
| `status` | string | `active`, `break`, or `handed`. |

### `guests/{guestId}`

| Field | Type | Notes |
|---|---|---|
| `ownerId` | string | Scopes the guest to one owner, reusable across that owner's events. |
| `displayName` | string | |
| `aliases` | array\<string\>? | Alternate spellings/names the same guest is known by. |
| `relation` | string? | e.g. "Groom's uncle". |

## Example documents

```json
// events/evt_001
{
  "name": "Priya's Wedding",
  "type": "wedding",
  "date": "2026-12-05T00:00:00Z",
  "familyName": "Rathod",
  "ownerId": "uid_owner1",
  "status": "live",
  "paymentTarget": { "upiId": "priyawedding@okaxis" },
  "members": {
    "uid_owner1": "owner",
    "uid_writer1": "writer",
    "uid_helper1": "helper"
  }
}
```

```json
// events/evt_001/entries/entry_001
{
  "guestId": "guest_042",
  "guestName": "Mr. Rathod",
  "type": "cash",
  "netAmount": 1500,
  "notesIn": { "500": 2, "200": 2, "100": 1 },
  "changeOut": { "100": 5 },
  "giftDescription": null,
  "photoUrl": null,
  "writerId": "uid_writer1",
  "serial": "W1-001",
  "createdAt": "2026-11-19T11:05:00Z",
  "confirmed": true,
  "voidedAt": null,
  "voidedReason": null
}
```

```json
// events/evt_001/entries/entry_001/history/hist_001
{
  "guestId": "guest_042",
  "previousValues": { "voidedAt": null, "voidedReason": null },
  "editedBy": "uid_writer1",
  "editedAt": "2026-11-19T11:10:00Z"
}
```

```json
// events/evt_001/boxes/uid_writer1
{
  "stock": { "500": 10, "200": 5, "100": 20 },
  "float": 2000,
  "status": "active"
}
```

```json
// guests/guest_042
{
  "ownerId": "uid_owner1",
  "displayName": "Mr. Rathod",
  "aliases": ["Rathod Uncle"],
  "relation": "Groom's uncle"
}
```

## Role rules summary

Enforced server-side in `firestore.rules`, keyed off `events/{eventId}.members`:

- `owner` — full read/write on the event, its entries, history, and boxes (any writer's); can update anyone's entries and the event doc itself; the only role that can still write once the event is `ended`.
- `writer` — reads entries/history/boxes; creates entries only with their own uid as `writerId`; updates only their own entries and only their own box doc; all writes stop once the event `status` is `ended` (read-only then).
- `helper` — can only **create** entries (with their own uid as `writerId`); has no read access to entries, history, or boxes at all — this is how "helpers never receive totals" is enforced at the rules level, not just in the UI.
- `viewer` — read-only on entries; no access to history or boxes.

`guests/{guestId}` is readable/writable only by its `ownerId`. **No path anywhere allows `delete`** — entries are voided (`voidedAt`/`voidedReason`), never deleted.

## Indexes

Deployed via `firestore.indexes.json`:

- Collection-group index on `entries`: (`guestId` ascending, `createdAt` descending) — "how much did this guest give, across every event" (the AI assistant's core question, D-011).
- Collection-group index on `history`: (`guestId` ascending, `editedAt` descending) — a guest's full edit/void audit trail across events.
- Collection index on `events/{eventId}/entries`: (`voidedAt` ascending, `serial` ascending) — list active entries for one event in order, without a client-side filter.

## Change log of the model

| Date | Change |
|---|---|
| 2026-11-19 | Initial seed: `events` (with `members` map), `entries`, `history` (as a sibling subcollection of `entries` under the event), `boxes`, and `guests`, with a minimal field set. |
| 2026-11-19 | Step 4.1 — reshaped per the real product requirements: `events` gained `name`/`type`/`date`/`familyName`/`status`/`paymentTarget`; `entries` gained `guestName`/`notesIn`+`changeOut` as per-denomination maps/`giftDescription`/`photoUrl`/`serial` (writer-prefixed)/`confirmed`/`voidedAt`; `history` moved to be a subcollection of each `entries/{entryId}` (was a sibling of `entries` under the event) and gained a denormalized `guestId`; `boxes` is now keyed by `writerId` with `stock`/`float`/`status` replacing the old `noteCount`/`verifiedTotal`/`handoverFrom`; `guests` fields renamed to `displayName`/`aliases`/`relation`. Added `firestore.rules` and `firestore.indexes.json`, deployed to the live `shagun-saathi` project. |
