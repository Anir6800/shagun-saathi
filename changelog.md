# Changelog

The running history of the project. **Newest first.** One entry per completed step. Never rewrite an old entry — if something needs correcting, add a new entry that says so.

Format: date, step id, what changed, why, files touched, how verified.

---

## 2026-11-19 — Step 2.7 — Create data-model.md
- What: Created `data-model.md` with sections: collections table, fields with types, example documents, role rules summary, indexes, and a change log of the model. Seeded `events/{eventId}` (members map), `entries` (guestId, type, netAmount, notesIn, changeOut, writerId, serial, voidedReason), `history`, `boxes` subcollections, and `guests/{guestId}` per owner. Linked it from `CONTEXT.md`'s index.
- Why: Step 2.7 — the data layer (Phase 4) needs one source of truth for the Firestore shape before any repository code is written.
- Files touched: `data-model.md` (new), `CONTEXT.md`, `memory.md`.
- How verified: Confirmed all 6 required sections are present, the `entries` fields match the task's exact list (guestId, type, netAmount, notesIn, changeOut, writerId, serial, voidedReason), example documents are valid JSON, and the role rules summary matches `members` roles from `RULES.md`/`decisions.md`. Confirmed `data-model.md`'s row in `CONTEXT.md` links to the file. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 2.6 — Create progress.md
- What: Restructured `progress.md` from a simple done-list into: a header (Current phase, Current step, Last updated), a table of 17 phases (exit criteria, status, date) spanning setup through release, and a table of steps (step id, title, tool, status, date, notes) covering every step completed so far plus the remaining Phase 2 steps. Linked it from `CONTEXT.md`'s index.
- Why: Step 2.6 — the project needs one place that shows exactly where it stands, at both the phase and step level.
- Files touched: `progress.md`, `CONTEXT.md`.
- How verified: Confirmed the phases table has exactly 17 rows, Phase 1 is marked `done` and Phase 2 `doing` (matching real history), all completed steps (1.1–2.6) are marked `done` with dates, and `progress.md`'s row in `CONTEXT.md` links to the file. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 2.5 — Create changelog.md
- What: Reformatted `changelog.md` into newest-first order with a step id in every entry heading (structural change only, no entry content rewritten), and added the missing earliest entry, Step 1.1 — Android project created (the pre-existing local Android Studio project and its Gradle sync fix). Linked it from `CONTEXT.md`'s index.
- Why: Step 2.5 — the task required newest-first ordering and step ids per entry, and asked for the first three entries (repo created, Android project created, context files created) to exist; two of those existed already in the wrong order/format, one (Android project created) was missing.
- Files touched: `changelog.md`, `CONTEXT.md`, `memory.md`, `progress.md`.
- How verified: Confirmed entries read newest-first, every heading carries a step id, and the three earliest entries (1.1 Android project created, 1.2 repo created, 2.1 context files created) are present at the bottom in correct chronological order. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 2.4 — Create decisions.md
- What: Rewrote `decisions.md` into an ID'd decision log (D-001–D-017): seeded the required D-001–D-013 product/architecture decisions (native Android+Compose, Firestore offline, Spark plan + Firebase AI Logic no Cloud Functions, Google+email auth, React/TS website on Firebase Hosting, public repo + GitHub Releases APK, monochrome glass UI, en/hi/gu, never holds money + UPI QR, never delete entries, AI tool-calling with code arithmetic, Stitch MCP design workflow, keep the name despite a naming clash), and migrated the 4 decisions this project had already made informally (docs-repo placement, deferred file stubs, google-services.json handling, memory.md restructure) into D-014–D-017 so none were lost. Added the never-edit-only-supersede rule at the top. Linked it from `CONTEXT.md`'s index.
- Why: Step 2.4 — a single decision log stops any future agent from re-litigating or contradicting a choice already made.
- Files touched: `decisions.md`, `CONTEXT.md`, `memory.md`, `progress.md`.
- How verified: Counted 13 seeded decisions matching the task's D-001–D-013 list exactly, confirmed every entry has all 6 required fields (ID, date, status, context, decision, alternatives, consequences), confirmed the supersede-don't-edit rule is stated, and confirmed the `decisions.md` row in `CONTEXT.md` links to the file. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 2.3 — Create memory.md
- What: Rewrote `memory.md` into the required section set (Project, Current state, Tech stack and versions, Architecture, Data model summary, Screens built, Integrations, How to run and test, Known issues, Next step), seeded with the known facts (Kotlin/Compose, Firestore offline persistence, free Firebase plan, Firebase AI Logic, Google/email sign-in, en/hi/gu, public GitHub repo, planned Firebase Hosting site with custom domain). Linked it from `CONTEXT.md`'s index.
- Why: Step 2.3 — any new agent needs a single file that gives the full project picture in about two minutes.
- Files touched: `memory.md`, `CONTEXT.md`, `decisions.md`, `progress.md`, `open-questions.md`.
- How verified: Checked `memory.md` has all 10 required sections, is seeded with every fact listed in the task's requirement 2, stays under 300 lines (currently ~45), and links out to `data-model.md`/`screens.md` instead of duplicating content not yet written. Confirmed the `memory.md` row in `CONTEXT.md` links to the file. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 2.2 — Create RULES.md
- What: Added `RULES.md` with sections for stack/versions, folder layout, naming, state handling, design rules, product rules, localisation, security, and process rules. Linked it from `CONTEXT.md`'s index.
- Why: Step 2.2 — every agent run needs a single rulebook of hard constraints to check against before and during a task.
- Files touched: `RULES.md` (new), `CONTEXT.md`, `memory.md`, `decisions.md`, `progress.md`.
- How verified: Manually checked `RULES.md` covers all 7 requirements from the task (stack/layout/naming/state, design, product, localisation, security, process) and matches the constraints already stated in the task prompt (never holds money, never deletes, integer-rupee arithmetic, helpers never see totals, no secrets, reduce-motion, 48dp targets). Confirmed `RULES.md` is linked in `CONTEXT.md`'s index table. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 2.1 — Create CONTEXT.md (context files created)
- What: Added `CONTEXT.md` as the context-file index, defining read order, update order, the "task not complete until files updated" rule, a session-start note, and recreate-from-template sections for every other context file.
- Why: Step 2.1 — every agent needs a single entry point describing which context files exist and how to use them.
- Files touched: `CONTEXT.md` (new), `memory.md`, `decisions.md`, `progress.md`, `open-questions.md`.
- How verified: Manually reviewed `CONTEXT.md` against the task's 6 requirements (index with purpose/when-read, read order, update order, the rule, session-start paragraph, templates section); confirmed via `git log`/`git status` that the commit landed on `dev`. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 — Step 1.2 — Repo created
- What: Created public GitHub repo `shagun-saathi` (README, Android `.gitignore`, empty `memory.md`/`changelog.md`). Created `main` and `dev` branches.
- Why: Step 1.2 — needed a place to track planning docs and, eventually, source for the project.
- Files touched: `README.md`, `.gitignore`, `memory.md` (empty), `changelog.md` (empty), repo/branch creation via `gh repo create`.
- How verified: Confirmed repo is public and both branches exist via `gh repo view`/`git branch -a`.

## 2026-11-19 — Step 1.1 — Android project created
- What: The local Android Studio project (`ShagunSaathi`, at `C:\Users\ANIRUDDH\AndroidStudioProjects\ShagunSaathi`) already existed as the starting point for this work, predating the GitHub repo. Its Gradle sync was failing (`services.gradle.org` timing out); fixed by manually downloading the Gradle 9.6.0 distribution over a slow connection, verifying its SHA-256 against `gradle-wrapper.properties`, placing it in the wrapper's dist cache, stopping a stuck Gradle daemon, and re-running sync.
- Why: Step 1.1 — needed a working Android project to build on before any other step could proceed.
- Files touched: `gradle/wrapper/gradle-wrapper.properties` (`networkTimeout` raised from 10000 to 60000), Gradle wrapper distribution cache (`~/.gradle/wrapper/dists/...`).
- How verified: `gradle_sync` tool reported "Sync finished successfully."
