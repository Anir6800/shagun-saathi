# Changelog

## 2026-11-19
- Created public GitHub repo `shagun-saathi` (README, Android `.gitignore`, empty `memory.md`/`changelog.md`).
- Created `main` and `dev` branches.

## 2026-11-19 (2)
- What: Added `CONTEXT.md` as the context-file index, defining read order, update order, the "task not complete until files updated" rule, a session-start note, and recreate-from-template sections for every other context file.
- Why: Step 2.1 — every agent needs a single entry point describing which context files exist and how to use them.
- Files touched: `CONTEXT.md` (new), `memory.md`, `decisions.md`, `progress.md`, `open-questions.md`.
- How verified: Manually reviewed `CONTEXT.md` against the task's 6 requirements (index with purpose/when-read, read order, update order, the rule, session-start paragraph, templates section); confirmed via `git log`/`git status` that the commit landed on `dev`. No app code or build involved, so no Gradle build/test run was applicable.

## 2026-11-19 (3)
- What: Added `RULES.md` with sections for stack/versions, folder layout, naming, state handling, design rules, product rules, localisation, security, and process rules. Linked it from `CONTEXT.md`'s index.
- Why: Step 2.2 — every agent run needs a single rulebook of hard constraints to check against before and during a task.
- Files touched: `RULES.md` (new), `CONTEXT.md`, `memory.md`, `decisions.md`, `progress.md`.
- How verified: Manually checked `RULES.md` covers all 7 requirements from the task (stack/layout/naming/state, design, product, localisation, security, process) and matches the constraints already stated in the task prompt (never holds money, never deletes, integer-rupee arithmetic, helpers never see totals, no secrets, reduce-motion, 48dp targets). Confirmed `RULES.md` is linked in `CONTEXT.md`'s index table. No app code or build involved, so no Gradle build/test run was applicable.
