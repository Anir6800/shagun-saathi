# Progress

Current phase: Phase 4 — Data layer
Current step: 4.1 — Data model and security rules (done); next up 3.3 (Storage/AI Logic, still open) or 4.2 (repositories/models)
Last updated: 2026-11-19

Update status when a step starts (`doing`) and when it finishes (`done`). List blockers under the step they block.

## Phases

| Phase | Exit criteria | Status | Date |
|---|---|---|---|
| 1. Project setup | Android project builds and syncs; public GitHub repo exists with `main`/`dev` branches | done | 2026-11-19 |
| 2. Context and process docs | `CONTEXT.md`, `RULES.md`, `memory.md`, `decisions.md`, `changelog.md`, `progress.md`, `data-model.md`, `screens.md`, `design-tokens.md`, `open-questions.md` all exist and are linked from `CONTEXT.md` | done | 2026-11-19 |
| 3. Firebase setup | Firebase project created; Auth (Google + email), Firestore (offline persistence), Storage, and Firebase AI Logic configured; `google-services.json` provisioned locally/CI | doing | 2026-11-19 |
| 4. Data layer | Repositories and models for events/entries/history/boxes/guests built per `data-model.md`; Firestore security rules written | doing | 2026-11-19 |
| 5. Design system from Stitch | Real Stitch screens pulled via MCP; `design-tokens.md` replaced with real values from the design | todo | |
| 6. Core navigation and app shell | Navigation Compose graph, app shell, bottom nav in place | todo | |
| 7. Auth screens | Splash, Onboarding, Sign in, Create account/reset password, Profile setup built | todo | |
| 8. Event management | Create event, Event dashboard, Invite writers, Join by scan built | todo | |
| 9. Entry recording | Add entry, Cash note counter, Change given, Gift entry, UPI QR built | todo | |
| 10. Entries list and corrections | Entries list, Edit or void built | todo | |
| 11. Cash box and handover | Cash box stock, Break and successor, Handover count, Handover summary built | todo | |
| 12. End of event and ledger | End event review, PDF preview/export built | todo | |
| 13. Guests and history | Guests list, Guest history built | todo | |
| 14. AI assistant | AI chat, Give-back advice built on tool-calling with code arithmetic | todo | |
| 15. Notebook capture and settings | Notebook capture, Notebook review, Writers and roles, Settings, App lock PIN built | todo | |
| 16. Website | Landing, Download APK, Login, Dashboard, Event ledger, Guest history, AI chat, PDF/export, Settings, Privacy/help built on Firebase Hosting with custom domain | todo | |
| 17. Release | Signed APK on GitHub Releases; website Download page live; Play Store decision made | todo | |

## Steps

| Step | Title | Tool | Status | Date | Notes |
|---|---|---|---|---|---|
| 1.1 | Android project created | Android Studio / Gradle | done | 2026-11-19 | Pre-existing local project; fixed Gradle sync (slow connection, manual distribution download). |
| 1.2 | Create the GitHub repo | gh CLI | done | 2026-11-19 | Public repo `shagun-saathi`, `main`/`dev` branches. |
| 2.1 | Create CONTEXT.md | Antigravity agent | done | 2026-11-19 | Index, read/update order, templates. |
| 2.2 | Create RULES.md | Antigravity agent | done | 2026-11-19 | Rulebook: stack, layout, naming, state, design, product, localisation, security, process. |
| 2.3 | Create memory.md | Antigravity agent | done | 2026-11-19 | Rewritten into fixed section set. |
| 2.4 | Create decisions.md | Antigravity agent | done | 2026-11-19 | Seeded D-001–D-017. |
| 2.5 | Create changelog.md | Antigravity agent | done | 2026-11-19 | Reformatted newest-first with step ids. |
| 2.6 | Create progress.md | Antigravity agent | done | 2026-11-19 | Restructured into phase/step tables. |
| 2.7 | Create data-model.md | Antigravity agent | done | 2026-11-19 | Collections, fields, examples, role rules, indexes, change log. |
| 2.8 | Create screens.md | Antigravity agent | done | 2026-11-19 | 42 screens seeded (32 Android + 10 Web). |
| 2.9 | Create design-tokens.md | Antigravity agent | done | 2026-11-19 | Seeded, all values marked replace-after-Stitch. |
| 2.10 | Create open-questions.md | Antigravity agent | done | 2026-11-19 | Q-001–Q-009 seeded; Q-002 answered via D-018. |
| 3.1 | Create the Firebase project (Spark plan) | Firebase console + CLI | done | 2026-11-19 | Done by product owner, verified via CLI. Follow-ups: package name is `com.example.shagunsaathi` (should be `com.shagunsaathi.app`); no debug SHA-1 registered. |
| 3.2 | Fix package name and register debug SHA-1 | Android Studio + Firebase CLI | done | 2026-11-19 | New Firebase app `com.shagunsaathi.app` with debug SHA-1/SHA-256. Sources moved. Kotlin pinned to 2.4.0 for firebase-auth 25. Build and unit tests pass. |
| 3.3 | Firebase Storage and Firebase AI Logic setup | Firebase console | todo | | |
| 4.1 | Data model and security rules | Antigravity agent + Firebase CLI | done | 2026-11-19 | Schema reshaped in data-model.md; firestore.rules + firestore.indexes.json written and deployed. |
| — | Move Android app into android/ (resolve Q-001) | Antigravity agent | done | 2026-11-19 | 42 files moved from the old AndroidStudioProjects path into this repo's dev branch. |
| — | Add real logo as launcher icon | Antigravity agent | done | 2026-11-19 | Legacy + adaptive + monochrome icon assets generated from the supplied PNG, all densities. Build verified. |
| 4.2 | Build repositories/models in data/ per the schema | Antigravity agent | todo | | |

## Blockers

- None.
