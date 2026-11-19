# Memory

The project's long-term memory. Read this to get the full picture in two minutes. Rewrite **Current state** and **Next step** after every task. Keep this file under 300 lines — move history to `changelog.md`.

## Project

Shagun Saathi is a free Android app and light website that replace the paper notebook used to record gifts (shagun) at weddings and family events in India. A writer records each guest's name and gift (cash, gift or online) in seconds, several writers can work at once, cash boxes are handed over with a verified note count, and at the end the family receives a PDF ledger. Years later an AI assistant can answer questions like "How much did Mr. Rathod give at my daughter's wedding?" Principles: ten seconds per guest, one hand, works offline; entries are never deleted (voided with a reason instead); private by default; the app records only and never holds money; respectful tone, because shagun is a blessing, not a transaction.

## Current state

- GitHub repo `Anir6800/shagun-saathi` created (public), with `main` and `dev` branches.
- Context/process docs exist: `CONTEXT.md` (index), `RULES.md` (rulebook), `memory.md` (this file), `decisions.md`, `changelog.md`, `progress.md`, `open-questions.md`.
- No app code written yet. No Android module, no Firebase project, no screens, no data layer.
- `data-model.md`, `screens.md`, `design-tokens.md` do not exist yet.
- Local Android Studio project at `C:\Users\ANIRUDDH\AndroidStudioProjects\ShagunSaathi` exists separately and is not yet a git repo (open question: merge into this repo or keep separate).

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

See `data-model.md` (not created yet). Known shape: `events/{id}` with a `members` map (owner, writer, helper, viewer roles) and subcollections `entries`, `history`, `boxes`; `guests/{id}` per owner.

## Screens built

See `screens.md` (not created yet). No screens built so far.

## Integrations

- Firebase project: not created yet — no project id to record.
- Auth providers planned: Google sign-in, email sign-in.
- Firebase AI Logic (Gemini): planned for the "ask about past gifts" assistant feature; not wired up yet.
- No secrets, keys, or `google-services.json` are stored in this repo or in this file — see `RULES.md` security rules.

## How to run and test

Not applicable yet — no Android module exists in this repo. Once the app module is created, this section must be rewritten with actual build/run/test commands (e.g. `./gradlew assembleDebug`, `./gradlew test`).

## Known issues

- No Firebase project exists yet, so Auth/Firestore/Storage/AI Logic are unconfigured.
- No decision yet on whether the existing local Android Studio project folder becomes this repo's app module.

## Next step

Create `data-model.md` (Firestore data model), per `progress.md`.
