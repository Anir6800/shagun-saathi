# Decisions

## 2026-11-19
- Repo created as a separate directory (`shagun-saathi`) rather than inside the existing Android Studio project folder (`ShagunSaathi`), since this repo holds planning/tracking docs (memory.md, progress.md, decisions.md, open-questions.md, changelog.md) distinct from the local Android app project, which isn't yet a git repo.
- Used `gh repo create` with `--gitignore Android --add-readme` for initial scaffolding.

## 2026-11-19 (2)
- `CONTEXT.md` lists `RULES.md`, `data-model.md`, `screens.md`, and `design-tokens.md` in its index, but these files do not exist yet. Rather than stub them out now (out of scope for the CONTEXT.md task), `CONTEXT.md` carries a recreate-from-template section for each, and their absence is tracked in `open-questions.md`.

## 2026-11-19 (3)
- `google-services.json` handling: never committed, kept out via `.gitignore`, provided locally or via CI secrets per environment. A real file wasn't added in this step (no CI/local secrets setup yet) — this is noted as a follow-up, not a contradiction of the no-secrets rule.

## 2026-11-19 (4)
- `memory.md` was restructured into the fixed section set required by its task (Project, Current state, Tech stack and versions, Architecture, Data model summary, Screens built, Integrations, How to run and test, Known issues, Next step), replacing its previous free-form User/Environment/Project layout. Nothing was lost: the prior content is folded into the new sections, and the original facts remain in `changelog.md`'s history.
