# Decisions

## 2026-11-19
- Repo created as a separate directory (`shagun-saathi`) rather than inside the existing Android Studio project folder (`ShagunSaathi`), since this repo holds planning/tracking docs (memory.md, progress.md, decisions.md, open-questions.md, changelog.md) distinct from the local Android app project, which isn't yet a git repo.
- Used `gh repo create` with `--gitignore Android --add-readme` for initial scaffolding.

## 2026-11-19 (2)
- `CONTEXT.md` lists `RULES.md`, `data-model.md`, `screens.md`, and `design-tokens.md` in its index, but these files do not exist yet. Rather than stub them out now (out of scope for the CONTEXT.md task), `CONTEXT.md` carries a recreate-from-template section for each, and their absence is tracked in `open-questions.md`.
