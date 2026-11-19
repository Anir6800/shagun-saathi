# Data Model

The single source of truth for the Firestore data shape. **Update this file in the same task that changes a collection, field, rule, or index** — never let it drift from the real schema.

Everything below is the **planned** shape (no Firebase project exists yet — see `open-questions.md` and `memory.md`). Money fields are always integer rupees (never floating point), per `RULES.md`.

## Collections

| Path | Kind | Purpose |
|---|---|---|
| `events/{eventId}` | top-level document | One wedding/family event. Holds the `members` role map. |
| `events/{eventId}/entries/{entryId}` | subcollection | One gift entry per guest contribution (cash, gift, or online/UPI). |
| `events/{eventId}/history/{historyId}` | subcollection | Append-only record of changes to entries (edits, voids) — never deleted. |
| `events/{eventId}/boxes/{boxId}` | subcollection | Cash box state: note counts, handovers between writers. |
| `guests/{guestId}` | top-level document | A guest, scoped per owner, reusable across that owner's events. |

## Fields

### `events/{eventId}`

| Field | Type | Notes |
|---|---|---|
| `ownerId` | string | uid of the event owner. |
| `title` | string | e.g. "Priya's Wedding". |
| `createdAt` | timestamp | |
| `members` | map\<uid, string\> | Role per member: `owner`, `writer`, `helper`, or `viewer`. |

### `events/{eventId}/entries/{entryId}`

| Field | Type | Notes |
|---|---|---|
| `guestId` | string | Reference to `guests/{guestId}`. |
| `type` | string | `cash`, `gift`, or `online`. |
| `netAmount` | int | Integer rupees. For `cash`, this is notes-in minus change-out. For `gift`/`online`, the recorded value. |
| `notesIn` | int | Integer rupees handed over by the guest (cash only). |
| `changeOut` | int | Integer rupees given back as change (cash only). |
| `writerId` | string | uid of the writer who recorded the entry. |
| `serial` | int | Sequential entry number within the event, for ordering/audit. |
| `voidedReason` | string? | Null/absent if active. Set when voided — the entry itself is never deleted (see D-010 in `decisions.md`). |
| `createdAt` | timestamp | |

### `events/{eventId}/history/{historyId}`

| Field | Type | Notes |
|---|---|---|
| `entryId` | string | Reference to the `entries` document this history row is about. |
| `action` | string | `created`, `edited`, or `voided`. |
| `changedBy` | string | uid of who made the change. |
| `changedAt` | timestamp | |
| `previousValues` | map | Snapshot of the fields before this change, for audit. |
| `reason` | string? | Required when `action` is `voided` (see D-010). |

### `events/{eventId}/boxes/{boxId}`

| Field | Type | Notes |
|---|---|---|
| `heldBy` | string | uid of the writer currently holding the box. |
| `noteCount` | map\<string, int\> | Count per denomination, e.g. `{"500": 10, "100": 20}`. |
| `verifiedTotal` | int | Integer rupees, sum of `noteCount`. |
| `handoverFrom` | string? | uid of the previous holder, set on a handover record. |
| `handoverAt` | timestamp? | |

### `guests/{guestId}`

| Field | Type | Notes |
|---|---|---|
| `ownerId` | string | Scopes the guest to one owner, reusable across that owner's events. |
| `name` | string | |
| `phone` | string? | Optional. |
| `notes` | string? | Optional, e.g. relationship. |

## Example documents

```json
// events/evt_001
{
  "ownerId": "uid_owner1",
  "title": "Priya's Wedding",
  "createdAt": "2026-11-19T10:00:00Z",
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
  "type": "cash",
  "netAmount": 1500,
  "notesIn": 2000,
  "changeOut": 500,
  "writerId": "uid_writer1",
  "serial": 1,
  "voidedReason": null,
  "createdAt": "2026-11-19T11:05:00Z"
}
```

```json
// events/evt_001/history/hist_001
{
  "entryId": "entry_001",
  "action": "voided",
  "changedBy": "uid_writer1",
  "changedAt": "2026-11-19T11:10:00Z",
  "previousValues": { "voidedReason": null },
  "reason": "Entered under the wrong guest name."
}
```

```json
// events/evt_001/boxes/box_001
{
  "heldBy": "uid_writer1",
  "noteCount": { "500": 10, "200": 5, "100": 20 },
  "verifiedTotal": 8000,
  "handoverFrom": null,
  "handoverAt": null
}
```

```json
// guests/guest_042
{
  "ownerId": "uid_owner1",
  "name": "Mr. Rathod",
  "phone": "9999999999",
  "notes": "Groom's uncle"
}
```

## Role rules summary

Per `events/{eventId}.members`:

- `owner` — full read/write on the event, its entries, history, and boxes; sees totals.
- `writer` — can create/edit entries and record box handovers; sees totals (needed to balance the box).
- `helper` — can create entries only; **never receives totals** (see `RULES.md`).
- `viewer` — read-only access to entries and the PDF ledger; no totals beyond what the owner chooses to share via the ledger.

`guests/{guestId}` is readable/writable only by its `ownerId`.

## Indexes

- `events/{eventId}/entries` composite index on (`voidedReason` ascending, `serial` ascending) — to list active entries in order without a client-side filter.
- `events/{eventId}/history` single-field index on `entryId` — to fetch a given entry's full history.
- `guests` single-field index on `ownerId` — to list an owner's guests across events.

(No Firestore project exists yet, so these are planned, not yet deployed — see `open-questions.md`.)

## Change log of the model

| Date | Change |
|---|---|
| 2026-11-19 | Initial seed: `events` (with `members` map), `entries`, `history`, `boxes` subcollections, and `guests`, with fields as above. |
