# Changelog

## 2026-11-19
- Created public GitHub repo `shagun-saathi` (README, Android `.gitignore`, empty `memory.md`/`changelog.md`).
- Created `main` and `dev` branches.

## 2026-11-19 (2)
- What: Added `CONTEXT.md` as the context-file index, defining read order, update order, the "task not complete until files updated" rule, a session-start note, and recreate-from-template sections for every other context file.
- Why: Step 2.1 — every agent needs a single entry point describing which context files exist and how to use them.
- Files touched: `CONTEXT.md` (new), `memory.md`, `decisions.md`, `progress.md`, `open-questions.md`.
- How verified: Manually reviewed `CONTEXT.md` against the task's 6 requirements (index with purpose/when-read, read order, update order, the rule, session-start paragraph, templates section); confirmed via `git log`/`git status` that the commit landed on `dev`. No app code or build involved, so no Gradle build/test run was applicable.
