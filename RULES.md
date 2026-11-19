# RULES.md — Project rulebook every agent run must follow

These rules are not suggestions. Violating one requires stopping and asking the product owner, not working around it silently.

## Stack and versions

- Kotlin, Jetpack Compose (Material 3, restyled — see `design-tokens.md`).
- Hilt for dependency injection.
- Navigation Compose for navigation.
- Coroutines and `StateFlow` for async work and state.
- Firebase Auth, Firestore (offline persistence on), Firebase Storage, Firebase AI Logic (Gemini).
- Free Firebase plan: there are no Cloud Functions. Anything that would need a Cloud Function must be done client-side or redesigned.

## Folder layout

One module, four top-level packages:

- `ui/` — Composables, screens, navigation graph. One ViewModel per screen.
- `domain/` — use cases and pure business logic, framework-agnostic.
- `data/` — repositories, Firestore/Storage/Auth access, data models.
- `di/` — Hilt modules.

A file belongs in the layer that matches what it does, not where it's convenient. UI code never talks to Firestore directly — it goes through a repository.

## Naming

- Composable functions: `PascalCase`, named after what they render (e.g. `GuestEntryRow`).
- ViewModels: `<Screen>ViewModel`, one per screen.
- Repositories: `<Domain>Repository` interface in `domain/`, `<Domain>RepositoryImpl` in `data/`.
- Use cases: verb phrase, e.g. `VoidEntryUseCase`.
- State classes: `<Screen>UiState`.

## State handling

- Each screen exposes a single `StateFlow<XyzUiState>` from its ViewModel; the Composable collects it with `collectAsStateWithLifecycle()`.
- No mutable state leaks out of a ViewModel — expose `StateFlow`, not `MutableStateFlow`.
- Unidirectional data flow: UI sends events/intents to the ViewModel, the ViewModel updates state, the UI renders state. No shortcuts where a Composable mutates shared state directly.

## Design rules

- Monochrome only — no introduced colour outside what `design-tokens.md` defines.
- Glass/blur values come only from `design-tokens.md`; never hardcode a blur radius or alpha inline.
- Animations use Compose's `animate*AsState`, `AnimatedVisibility`, `Animatable` and related APIs — no manual frame timers.
- Honour the system reduce-motion setting: when it's on, skip or shorten non-essential animations.
- Touch targets are at least 48dp.

## Product rules

- Entries are never deleted. To remove one, void it with a reason; the original stays in history.
- The app never holds money — it only records what was given. No payment processing, no balances held on behalf of a user.
- All money arithmetic happens in Kotlin using integer rupees. Never use `Float`/`Double` for money.
- Helpers never receive totals — they can record entries but cannot see aggregate sums for the event.

## Localisation rule

- Every user-facing string lives in `strings.xml`, translated for `en`, `hi` and `gu`. No hardcoded UI strings in Kotlin or Composables.

## Security rules

- No keys, keystores, passwords, or service-account credentials committed to the repository.
- `google-services.json` is never committed. It's listed in `.gitignore` and provided locally/via secure CI secrets per environment; a placeholder/template (no real values) may be checked in if needed for onboarding.
- API keys for Firebase AI Logic (Gemini) and any other service are read from local/CI secrets, never inlined in source.

## Process rules

- Read before start and update after finish exactly as defined in `CONTEXT.md` (read order, update order, the completion rule).
- One task per agent run. Don't bundle unrelated work into a single run.
- Small commits on `dev`, with the step id in the commit message (e.g. `Add RULES.md (step 2.2)`).
