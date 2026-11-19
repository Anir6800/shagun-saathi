# Decisions

The decision log. No agent may contradict an earlier accepted decision without first asking the product owner. **Never edit an accepted entry** — if a decision changes, add a new entry that supersedes it (and mark the old one's status as `Superseded by D-0xx`).

Entry format: ID, date, status (`Accepted` or `Superseded`), context, decision, alternatives considered, consequences.

---

## D-001
- Date: 2026-11-19
- Status: Accepted
- Context: Need to pick the app platform.
- Decision: Native Android with Kotlin and Jetpack Compose.
- Alternatives considered: Flutter — rejected (team is Android-native, wants direct platform access for things like UPI intents and notifications without a cross-platform abstraction layer).
- Consequences: No iOS app from this codebase. Full access to Android platform APIs and Compose tooling.

## D-002
- Date: 2026-11-19
- Status: Accepted
- Context: Need offline-first data storage since writers record guests without reliable connectivity at events.
- Decision: Firestore with offline persistence enabled.
- Alternatives considered: Room + custom sync — rejected (would mean building and maintaining a sync engine ourselves); Firebase Realtime Database — rejected (weaker querying for the entries/history/boxes model).
- Consequences: Local cache handles offline writes/reads automatically; sync conflicts are resolved by Firestore's own rules; app depends on the Firebase SDK everywhere data is touched.

## D-003
- Date: 2026-11-19
- Status: Accepted
- Context: Need to keep the project free to run at the expected scale, and need an AI assistant feature.
- Decision: Free Firebase Spark plan. AI is called directly from the app through Firebase AI Logic (Gemini). No Cloud Functions.
- Alternatives considered: Blaze plan with Cloud Functions as a backend — rejected (introduces cost and a server component this project doesn't need yet).
- Consequences: All business logic runs on-device or through Firebase AI Logic; no server-side code; any feature that would need a Cloud Function must be redesigned to run client-side instead.

## D-004
- Date: 2026-11-19
- Status: Accepted
- Context: Need sign-in that's simple for families and writers across India.
- Decision: Google sign-in plus email and password. No phone OTP.
- Alternatives considered: Phone OTP — rejected (adds SMS cost/complexity and carrier reliability issues); social logins beyond Google — rejected (not needed for the target audience).
- Consequences: Users without a Google account can still sign in with email/password; no SMS verification infrastructure needed.

## D-005
- Date: 2026-11-19
- Status: Accepted
- Context: Need a public-facing website alongside the Android app, without duplicating full app functionality on the web.
- Decision: Website is a light React and TypeScript app hosted on Firebase Hosting with a custom domain. The Android app gets the full feature set; the website does not try to match it.
- Alternatives considered: Full-featured web app mirroring Android — rejected (doubles the maintenance surface for a product principle built around "one hand, ten seconds per guest" mobile use).
- Consequences: Website scope stays intentionally small (marketing, APK download, possibly a lightweight viewer); any feature parity request must be weighed against this decision.

## D-006
- Date: 2026-11-19
- Status: Accepted
- Context: Need a distribution and visibility model for the app.
- Decision: Public GitHub repo. The signed APK is published via GitHub Releases, and the website has a Download page linking to it.
- Alternatives considered: Google Play Store only — not rejected outright but not the initial distribution channel; private repo — rejected (project wants to be open about its source).
- Consequences: No Play Store review gate for initial distribution; releases must be signed and versioned manually; users sideload the APK or use the website's Download page.

## D-007
- Date: 2026-11-19
- Status: Accepted
- Context: Need a visual identity for the app.
- Decision: Monochrome glass UI, fully animated, with a floating pill bottom nav.
- Alternatives considered: Standard Material 3 colour theming — rejected in favour of a distinct, calmer monochrome look appropriate to the app's respectful tone.
- Consequences: All colour, radius, and blur values must come from `design-tokens.md` (see `RULES.md`); no ad hoc colour introduced per screen.

## D-008
- Date: 2026-11-19
- Status: Accepted
- Context: Need to serve users across language backgrounds in India.
- Decision: Support English, Hindi, and Gujarati.
- Alternatives considered: English-only launch — rejected (target audience spans Gujarati- and Hindi-speaking families).
- Consequences: Every user-facing string must exist in `strings.xml` for `en`, `hi`, and `gu` (see `RULES.md`).

## D-009
- Date: 2026-11-19
- Status: Accepted
- Context: Need to record online/UPI gifts without the app touching money.
- Decision: The app records only and never holds money. Online payment is represented as a UPI QR code, generated from a typed UPI ID or an uploaded QR image — the app never processes the payment itself.
- Alternatives considered: Integrating a payment gateway/UPI SDK that moves money through the app — rejected outright; this contradicts the core "never holds money" principle.
- Consequences: No payment processing code, no PCI/payment compliance burden; the app only displays/generates a QR for the guest to pay the family directly.

## D-010
- Date: 2026-11-19
- Status: Accepted
- Context: Need to protect the integrity of the gift ledger, including corrections.
- Decision: Entries are never deleted. A wrong or retracted entry is voided with a reason instead, and the original stays visible in history.
- Alternatives considered: Hard delete with an audit log — rejected (an audit log is extra infrastructure to reinvent what voiding already gives for free, and "deleted" entries could be misread as data loss by the family).
- Consequences: All entry-removal UI must implement "void with reason," never a destructive delete; `history` subcollection is the source of truth for what happened to an entry over time.

## D-011
- Date: 2026-11-19
- Status: Accepted
- Context: Need an AI assistant that answers questions about past gifts without risking wrong arithmetic from an LLM.
- Decision: The AI answers by tool calling (querying structured data), and all arithmetic is done by code, never by the model.
- Alternatives considered: Letting the AI compute sums/totals directly from retrieved text — rejected (LLMs are unreliable at exact arithmetic; money figures must be exact).
- Consequences: Every AI-exposed "how much/who gave" question is backed by a deterministic Kotlin query/calculation; the model's job is only to interpret the question and explain the (code-computed) result.

## D-012
- Date: 2026-11-19
- Status: Accepted
- Context: Need an efficient design workflow for a monochrome glass UI across many screens.
- Decision: Design happens in Stitch, one screen per prompt, with the Stitch project connected to Antigravity through the Stitch MCP.
- Alternatives considered: Hand-coding Compose UI from scratch per screen without a design tool — rejected (slower iteration, harder to keep visual consistency across the full screen set).
- Consequences: `design-tokens.md` and `screens.md` should trace back to the Stitch project "Shagun Saathi Android"; any agent building UI should read the relevant Stitch screen via the MCP when available before implementing from scratch.

## D-013
- Date: 2026-11-19
- Status: Accepted
- Context: Another website already uses the name "Shagun Saathi."
- Decision: Keep the name Shagun Saathi anyway.
- Alternatives considered: Renaming the app/product — rejected (the name fits the product's purpose and tone well; the other site's use was judged not to create a conflict worth a rename).
- Consequences: No trademark/branding search or rename work is planned as part of this project; this should be revisited only if an actual legal conflict arises.

## D-014
- Date: 2026-11-19
- Status: Accepted
- Context: Needed somewhere to track planning/process docs (`memory.md`, `progress.md`, `decisions.md`, `open-questions.md`, `changelog.md`) for the project.
- Decision: Created a separate GitHub repo directory (`shagun-saathi`) for these docs, rather than placing them inside the existing local Android Studio project folder (`ShagunSaathi`), which isn't yet a git repo.
- Alternatives considered: Initializing git directly inside the existing Android Studio project folder — not done yet, left as an open question.
- Consequences: Planning docs and app source currently live in different places; see the open question on whether to merge them.

## D-015
- Date: 2026-11-19
- Status: Accepted
- Context: `CONTEXT.md`'s index names `RULES.md`, `data-model.md`, `screens.md`, and `design-tokens.md`, but at the time only some of these existed yet.
- Decision: Don't stub out all referenced files immediately; instead, `CONTEXT.md` carries a recreate-from-template section for each, and any still missing are tracked in `open-questions.md` until their own task creates them.
- Alternatives considered: Creating empty placeholder files for everything up front — rejected as unnecessary churn ahead of the step that actually needs each file.
- Consequences: A file listed in `CONTEXT.md`'s index may not exist yet; check `open-questions.md` and `progress.md` before assuming it does.

## D-016
- Date: 2026-11-19
- Status: Accepted
- Context: `RULES.md` needed a position on `google-services.json`, which is required for Firebase but must never be committed.
- Decision: `google-services.json` is never committed, is listed in `.gitignore`, and is provided locally or via CI secrets per environment.
- Alternatives considered: Committing a placeholder with real-looking but fake values — rejected as unnecessary risk/confusion; simplest to just document the rule and leave actual secret provisioning to a later, dedicated step.
- Consequences: No Firebase-dependent code can be run/tested until a local or CI secret-provisioning mechanism exists — tracked in `open-questions.md`.

## D-017
- Date: 2026-11-19
- Status: Accepted
- Context: `memory.md`'s task required a fixed section structure (Project, Current state, Tech stack and versions, Architecture, Data model summary, Screens built, Integrations, How to run and test, Known issues, Next step).
- Decision: Replaced `memory.md`'s prior free-form User/Environment/Project layout with this fixed structure.
- Alternatives considered: Keeping the old layout and appending the new sections — rejected (task explicitly asked for this section set, and keeping both would bloat the file past its 300-line budget over time).
- Consequences: Any future edit to `memory.md` must preserve this section set; the prior free-form content's facts were folded into the new sections, with history preserved in `changelog.md`.
