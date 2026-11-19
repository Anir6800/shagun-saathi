# Open Questions

The list of things still unknown. Add a question as soon as it appears. When answered, move the answer into `decisions.md` (as a new decision) and mark the question here `answered` with a short pointer to that decision.

Entry format: ID, question, why it matters, owner, status (`open` or `answered`), answer, date.

---

## Q-001
- Question: Should the local Android Studio project (`C:\Users\ANIRUDDH\AndroidStudioProjects\ShagunSaathi`, not yet a git repo) be moved into the `shagun-saathi` GitHub repo, or kept separate with that repo only for planning docs?
- Why it matters: Determines where app source code will actually live relative to the context/process docs, and whether two repos need to stay in sync.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-002
- Question: Should `data-model.md`, `screens.md`, and `design-tokens.md` be created as separate upcoming steps rather than immediately when first referenced in `CONTEXT.md`?
- Why it matters: Affects whether `CONTEXT.md`'s index can be trusted to mean "this file exists" versus "this file is planned."
- Owner: Product owner
- Status: answered
- Answer: Yes — created as their own dedicated steps (2.7, 2.8, 2.9). All three now exist. See D-015 in `decisions.md` for the original reasoning not to stub them early.
- Date: 2026-11-19

## Q-003
- Question: Where should `google-services.json` and other Firebase/Gemini secrets be stored per environment (local dev machine vs. CI)?
- Why it matters: No Firebase-dependent code can be run or tested until this is decided; blocks Phase 3 (Firebase setup).
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-004
- Question: Which exact models appear in the Antigravity model dropdown, and which should this project standardize on?
- Why it matters: Affects reproducibility of agent behaviour across sessions and which model capabilities (context size, tool calling) can be relied on.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-005
- Question: What are the exact Stitch MCP connection details (how to connect Antigravity to the Stitch project "Shagun Saathi Android")?
- Why it matters: Blocks Phase 5 (design system from Stitch) and any screen-building step that should read real Stitch screens instead of guessing from `screens.md`/`design-tokens.md` seed values.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-006
- Question: What is the final custom domain name for the website (Firebase Hosting)?
- Why it matters: Needed before the website (Phase 16) can be deployed and before the Download page / APK links can be finalized.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-007
- Question: How should Play Integrity and App Check be set up for this project?
- Why it matters: Affects anti-abuse protection for Firestore/Storage/AI Logic calls from the app; needs deciding before Phase 3 (Firebase setup) is considered complete.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-008
- Question: What are the Gemini free-quota limits under the current Firebase AI Logic setup, and are they sufficient for expected usage?
- Why it matters: The AI assistant feature (AI chat, Give-back advice) depends on staying within free quota, per D-003 (free Spark plan, no Cloud Functions).
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-010
- Question: Should the Android app id be changed from `com.example.shagunsaathi` to `com.shagunsaathi.app`? That would mean registering a new Android app in Firebase and downloading a new `google-services.json`.
- Why it matters: The step 3.1 task named `com.shagunsaathi.app`, but the Firebase app and the Gradle `applicationId`/`namespace` all use `com.example.shagunsaathi`. Releases can't ship with a `com.example` id, and the id can't change after the first public APK.
- Owner: Product owner
- Status: answered
- Answer: Yes. Changed to `com.shagunsaathi.app` in step 3.2. See D-020.
- Date: 2026-11-19

## Q-011
- Question: Is the Firebase project definitely still on the Spark (free) plan, and are both Google and Email/Password turned on under Auth → Sign-in method?
- Why it matters: The CLI can't confirm the billing plan or the auth providers. D-003 requires the Spark plan.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19

## Q-009
- Question: Should the app be published on Google Play at some point, in addition to the GitHub Releases APK?
- Why it matters: Affects signing/release process, review compliance work, and whether Play-specific APIs (e.g. Play Integrity) should be adopted now or later.
- Owner: Product owner
- Status: open
- Answer: —
- Date: 2026-11-19
