# Memory

The project's long-term memory. Read this to get the full picture in two minutes. Rewrite **Current state** and **Next step** after every task. Keep this file under 300 lines — move history to `changelog.md`.

## Project

Shagun Saathi is a free Android app and light website that replace the paper notebook used to record gifts (shagun) at weddings and family events in India. A writer records each guest's name and gift (cash, gift or online) in seconds, several writers can work at once, cash boxes are handed over with a verified note count, and at the end the family receives a PDF ledger. Years later an AI assistant can answer questions like "How much did Mr. Rathod give at my daughter's wedding?" Principles: ten seconds per guest, one hand, works offline; entries are never deleted (voided with a reason instead); private by default; the app records only and never holds money; respectful tone, because shagun is a blessing, not a transaction.

## Current state

- GitHub repo `Anir6800/shagun-saathi` created (public), with `main` and `dev` branches.
- All 10 context/process docs now exist: `CONTEXT.md`, `RULES.md`, `memory.md`, `decisions.md` (D-001–D-018, all Accepted), `changelog.md`, `progress.md`, `data-model.md`, `screens.md`, `design-tokens.md`, `open-questions.md` (Q-001–Q-009). Phase 2 (context/process docs) is complete.
- Firebase project `shagun-saathi` exists. Firestore `(default)` is in `asia-south1` (Native mode), Google and Email/Password auth are on, and one Android app is registered. The local Android project has the google-services plugin, firebase-auth and firebase-firestore wired in. The app id is `com.shagunsaathi.app`, with the debug SHA-1 and SHA-256 registered in Firebase. Kotlin is pinned to 2.4.0. Debug build and unit tests pass.
- The full Firestore schema is designed and `firestore.rules`/`firestore.indexes.json` are written and **deployed** to the live `shagun-saathi` project (step 4.1). Roles (`owner`/`writer`/`helper`/`viewer`) are enforced server-side via `events/{eventId}.members`; no path allows `delete`; helpers have zero read access to `entries`/`history`/`boxes` (this is how "helpers never receive totals" is enforced, not just a UI choice). See `data-model.md` for the full schema.
- Not yet in place: Compose/Hilt, screens, repositories/ViewModels (the data layer's Kotlin code — only the server-side schema/rules exist so far), Storage, AI Logic.
- `data-model.md`, `screens.md`, and `design-tokens.md` all now exist. `design-tokens.md` holds seed values only, marked "replace after Stitch".
- The Android app now lives at `android/` in this repo (resolved Q-001, D-024). The old `C:\Users\ANIRUDDH\AndroidStudioProjects\ShagunSaathi` path's empty local `.git` has been removed; edit `shagun-saathi/android/` going forward (open that folder in Android Studio, not the old path).

## Tech stack and versions

- Kotlin, Jetpack Compose (Material 3, restyled — monochrome, glass values from `design-tokens.md`).
- Hilt (DI), Navigation Compose, Coroutines + `StateFlow`.
- Firebase Auth (Google and email sign-in), Firestore with offline persistence, Firebase Storage, Firebase AI Logic (Gemini).
- Free Firebase plan: no Cloud Functions.
- Languages: English, Hindi, Gujarati (every user-facing string in `strings.xml` for `en`, `hi`, `gu`).
- Website: planned on Firebase Hosting with a custom domain (not yet set up).
- Specific library versions: not yet pinned — no `build.gradle` exists yet.

## Architecture

MVVM with repositories. Layers: `ui/` (Composables, screens, nav graph, one ViewModel per screen), `domain/` (use cases, framework-agnostic business logic), `data/` (repositories, Firestore/Storage/Auth access), `di/` (Hilt modules). Unidirectional data flow: UI sends events to the ViewModel, the ViewModel updates a single `StateFlow<UiState>`, the UI renders it. Full detail in `RULES.md`.

## Data model summary

See `data-model.md` for the full schema and `firestore.rules`/`firestore.indexes.json` for the enforced rules (deployed to the live project). One example document per collection:

```json
// events/evt_001
{ "name": "Priya's Wedding", "type": "wedding", "date": "2026-12-05T00:00:00Z",
  "familyName": "Rathod", "ownerId": "uid_owner1", "status": "live",
  "paymentTarget": { "upiId": "priyawedding@okaxis" },
  "members": { "uid_owner1": "owner", "uid_writer1": "writer", "uid_helper1": "helper" } }
```
```json
// events/evt_001/entries/entry_001
{ "guestId": "guest_042", "guestName": "Mr. Rathod", "type": "cash", "netAmount": 1500,
  "notesIn": { "500": 2, "200": 2, "100": 1 }, "changeOut": { "100": 5 },
  "giftDescription": null, "photoUrl": null, "writerId": "uid_writer1", "serial": "W1-001",
  "createdAt": "2026-11-19T11:05:00Z", "confirmed": true, "voidedAt": null, "voidedReason": null }
```
```json
// events/evt_001/entries/entry_001/history/hist_001
{ "guestId": "guest_042", "previousValues": { "voidedAt": null }, "editedBy": "uid_writer1",
  "editedAt": "2026-11-19T11:10:00Z" }
```
```json
// events/evt_001/boxes/uid_writer1
{ "stock": { "500": 10, "200": 5, "100": 20 }, "float": 2000, "status": "active" }
```
```json
// guests/guest_042
{ "ownerId": "uid_owner1", "displayName": "Mr. Rathod", "aliases": ["Rathod Uncle"], "relation": "Groom's uncle" }
```

## Screens built

See `screens.md` for the full map (42 screens: 32 Android + 10 Web, all currently `planned`/`todo`). No screens built so far.

## Integrations

- Firebase project id: `shagun-saathi` (number 354786670547), Spark plan. Firestore region: `asia-south1`.
- Android app in Firebase: `1:354786670547:android:d0b8dd3629d3f61fd62126` (package `com.shagunsaathi.app`). The old `com.example.shagunsaathi` app is unused (D-020).
- Auth providers planned: Google sign-in, email sign-in.
- Firebase AI Logic (Gemini): planned for the "ask about past gifts" assistant feature; not wired up yet.
- No secrets, keys, or `google-services.json` are stored in this repo or in this file — see `RULES.md` security rules.
- `firestore.rules` and `firestore.indexes.json` live in the Android project root and are deployed with `firebase deploy --only firestore:rules,firestore:indexes --project shagun-saathi` (`.firebaserc` pins the default project). No secrets in either file.

## How to run and test

Not applicable yet — no Android module exists in this repo. Once the app module is created, this section must be rewritten with actual build/run/test commands (e.g. `./gradlew assembleDebug`, `./gradlew test`).

## Known issues

- Storage and Firebase AI Logic are not set up yet.
- A release signing key doesn't exist yet. Its SHA-1 must be added to Firebase when it does.
- No repositories/ViewModels exist yet to actually write/read this schema from the app — only the server-side rules and schema docs exist.
- No decision yet on whether the existing local Android Studio project folder becomes this repo's app module.

## Next step

Step 4.2 (or 3.3, whichever the product owner wants next): either build the `data/` repositories/models for this schema, or finish Phase 3 (Storage, Firebase AI Logic).
