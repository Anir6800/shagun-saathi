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

## D-018
- Date: 2026-11-19
- Status: Accepted
- Context: Q-001 in `open-questions.md` asked whether `data-model.md`, `screens.md`, and `design-tokens.md` should be created as their own dedicated steps rather than stubbed immediately.
- Decision: Yes — created as steps 2.7, 2.8, and 2.9 respectively. All three now exist.
- Alternatives considered: None new; this confirms the approach already taken in D-015.
- Consequences: `open-questions.md` Q-002 is marked `answered` pointing here.

## D-019
- Date: 2026-11-19
- Status: Accepted
- Context: The Firebase backend had to be created (step 3.1), within D-003's free-plan rule.
- Decision: Firebase project `shagun-saathi` on the Spark plan. Firestore `(default)` database in Native mode, region `asia-south1` (Mumbai), production-mode rules. Auth providers: Google and Email/Password. `google-services.json` lives only in `app/` on the local machine and is git-ignored.
- Alternatives considered: Other regions were rejected, because `asia-south1` is closest to users in India and Firestore's location can't be changed later. Test-mode rules were rejected because the data is private by default.
- Consequences: The region is fixed for good. Every read and write needs real security rules before the app works. Firebase config is pinned in `gradle/libs.versions.toml`: google-services 4.6.0, firebase-auth 25.0.0, firebase-firestore 27.2.0.

## D-020
- Date: 2026-11-19
- Status: Accepted
- Context: Q-010. The app id was `com.example.shagunsaathi`.
- Decision: The app id and namespace are `com.shagunsaathi.app`. It is registered as a new Firebase Android app with the debug SHA-1 and SHA-256. The old `com.example.shagunsaathi` Firebase app stays registered but unused.
- Alternatives considered: Keeping `com.example.*`, rejected because it can't be published and can't change after release.
- Consequences: Each new signing key (release, CI) needs its SHA-1 added to the `com.shagunsaathi.app` Firebase app. The unused old app can be deleted from the console later.

## D-021
- Date: 2026-11-19
- Status: Accepted
- Context: firebase-auth 25.0.0 is compiled with Kotlin 2.4 metadata, but AGP 9.5.1's built-in Kotlin is 2.2, so compilation failed.
- Decision: Pin Kotlin 2.4.0 by declaring `org.jetbrains.kotlin.android` with `apply false` in the root `build.gradle.kts`.
- Alternatives considered: Downgrading firebase-auth, rejected because it holds the project on old Firebase. Skipping the metadata check with a compiler flag, rejected as a hack.
- Consequences: Kotlin upgrades are now a deliberate catalog bump (`kotlin` in `libs.versions.toml`), not tied to AGP's bundled version.

## D-022
- Date: 2026-11-19
- Status: Accepted
- Context: Step 4.1 required Firestore security rules that enforce roles, deny all deletes, let a writer create entries only under their own uid, let only the owner edit someone else's entry, make writers read-only once an event ends, and block helpers from totals.
- Decision: `firestore.rules` (deployed to the live `shagun-saathi` project) encodes all of this server-side: role comes from `events/{eventId}.members[uid]`; every `delete` rule is `false`; entry `create` requires `request.resource.data.writerId == request.auth.uid`; entry `update` requires either the owner role or (writer role + `resource.data.writerId == request.auth.uid` + event `status == 'live'`); `boxes` writes follow the same own-box-only + live-event pattern. Helpers get **no** `get`/`list` rule on `entries`, `history`, or `boxes` at all — not a restricted view, a flat deny — which is how "helpers never receive totals" (RULES.md) is enforced at the data layer instead of only in the UI.
- Alternatives considered: Letting helpers read entries but hiding totals client-side — rejected, because a modified client or direct API call would then be able to read every amount; a security rule is the only enforcement that can't be bypassed by the app itself. Cloud Functions as a write gateway — rejected, no Cloud Functions on the Spark plan (D-003).
- Consequences: Any future screen helpers need (e.g. confirming their own entry was saved) must come from what the create response already echoes back to the client, not from re-reading the entry; a helper-facing "my recent entries" screen cannot be built against Firestore directly without changing this rule.

## D-023
- Date: 2026-11-19
- Status: Accepted
- Context: Step 4.1 needed `events/{eventId}/entries/{entryId}` to carry the full set of product fields (guest name, per-denomination cash, gift description, photo, confirmation, void timestamp) and a way to answer "how much did this guest give, ever" across events.
- Decision: Reshaped the schema from the step 2.7 seed: `history` moved from a sibling of `entries` under the event to a subcollection of each `entries/{entryId}` document, and gained a denormalized `guestId` field; `boxes` documents are now keyed by the writer's own uid (`boxes/{writerId}`) instead of a random box id, holding `stock`/`float`/`status`; `notesIn`/`changeOut` became denomination-keyed maps instead of single ints; `serial` is now a writer-prefixed string (e.g. `"W1-007"`) instead of a global int, so two writers numbering entries offline never collide.
- Alternatives considered: Keeping `history` as a flat sibling subcollection — rejected, because nesting it under the entry it describes makes per-entry audit reads a single subcollection fetch instead of a filter over a shared collection, and the server-side rule for "only the owner or that entry's writer can read its history" becomes a direct parent-id check instead of a stored `entryId` field. A single global `serial` counter — rejected, because two offline writers would need to coordinate a shared counter, which online-only Firestore transactions can't guarantee while offline (D-002).
- Consequences: `data-model.md`'s change log records this as a breaking reshape of the step 2.7 seed (no app code existed yet to migrate, so no backward-compatibility work was needed). The `guestId` field on `history` is now load-bearing for the collection-group "guest history" index (D-011's AI assistant feature) — any future code writing a history doc must set it, or that query silently misses rows.

## D-024
- Date: 2026-11-19
- Status: Accepted
- Context: Q-001. The local Android project (`C:\Users\ANIRUDDH\AndroidStudioProjects\ShagunSaathi`) had just gotten a fresh local git repo (no commits, no remote) and needed a home on GitHub.
- Decision: Moved the Android project into this same repo (`Anir6800/shagun-saathi`), under a new `android/` folder on `dev`, rather than a separate repo. Build artifacts, `.gradle/`, `.idea/`, `local.properties`, and `google-services.json*` are excluded by the repo's existing root `.gitignore`.
- Alternatives considered: A separate `shagun-saathi-android` repo — rejected by the product owner in favour of one repo for docs and app code (matches D-006's "public GitHub repo" framing as a single project home).
- Consequences: `Q-001` is resolved — app code and planning docs live together from now on. The Android project's own standalone local `.git` (still present at the old path, zero commits) is now redundant; it wasn't deleted, since deleting a `.git` directory is a one-way action and it's harmless left in place. Future work should edit files under `shagun-saathi/android/`, not the old `AndroidStudioProjects\ShagunSaathi` path, to avoid the two copies drifting apart. (Note: that old `.git` was later removed, once confirmed to hold zero commits — see the changelog.)

## D-025
- Date: 2026-11-19
- Status: Accepted
- Context: The product owner supplied the real app logo (a monochrome black/white diamond mark). The app still had the Android Studio default green robot/grid placeholder icon.
- Decision: The logo is now the app's launcher icon everywhere: legacy `ic_launcher`/`ic_launcher_round` at all 5 densities, the adaptive icon's foreground (scaled to the 66% safe zone, transparent padding) and background (solid black, `@color/launcher_background`, matching the logo's own black canvas so there's no seam), and a generated monochrome (alpha-only white silhouette) layer for Android 13+ themed icons. The raw source PNG is also kept at `app/src/main/res/drawable-nodpi/logo_mark.png` for reuse in-app later (Splash, Settings/About).
- Alternatives considered: Asking for a vector (SVG) source and hand-tracing it into an XML `ic_launcher_foreground.xml` — rejected for now since only a flattened PNG was provided; a raster adaptive-icon foreground is valid and simpler, at the cost of not scaling losslessly to arbitrary sizes. The monochrome layer was derived by thresholding luminance into an alpha channel rather than hand-authoring a separate silhouette — a pragmatic seed, not a hand-tuned asset.
- Consequences: The old `ic_launcher_background.xml`/`ic_launcher_foreground.xml` vector drawables (green robot placeholder) are deleted. If Stitch (D-012) later supplies a true vector version of the mark, the adaptive-icon foreground/monochrome should be redone as XML vector drawables instead of raster PNGs, for crisper scaling — tracked as Q-014 in `open-questions.md`.
