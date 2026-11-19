# CONTEXT.md — Index for every agent

This file is the entry point. Read it first in every new session, before touching any code or other context file.

## Context files

| File | Purpose | Read when |
|---|---|---|
| `CONTEXT.md` | This index: what files exist, read order, update order, session start rule. | Always, first. |
| [`RULES.md`](RULES.md) | Hard constraints and conventions every agent must follow (money arithmetic, no deletes, string resources, design tokens, etc.). Violating these requires stopping to ask. | Always, before any task. |
| `memory.md` | Durable facts about the user, environment, and project that should survive across sessions (not task-specific). | Always, before any task. |
| `decisions.md` | Dated log of product/architecture decisions and their rationale. A task that contradicts this file must stop and ask. | Always, before any task. |
| `changelog.md` | Dated log of what changed, why, files touched, and how it was verified. History/reference, not a pre-task read. | When investigating history or writing a new entry. |
| `progress.md` | Checklist of steps/tasks with status and the current next step. | Always, before any task. |
| `data-model.md` | Firestore data model: `events/{id}` member roles, `entries`/`history`/`boxes` subcollections, `guests/{id}`, field definitions. | When a task touches data, repositories, or Firestore rules. |
| `screens.md` | Screen inventory, navigation graph, per-screen purpose, states, and key UI behaviour. | When a task touches UI, navigation, or ViewModels. |
| `design-tokens.md` | Colours, radii, blur, spacing, and other tokens sourced from the Stitch design. | When a task touches UI styling. |
| `open-questions.md` | Unresolved questions that need the product owner's input before proceeding. | Always, before any task; add to it whenever a task raises a new question. |

## Read order (before any task)

1. `CONTEXT.md`
2. `RULES.md`
3. `memory.md`
4. `decisions.md`
5. `progress.md`
6. `open-questions.md`
7. `data-model.md`, `screens.md`, `design-tokens.md` — only the ones relevant to the task at hand.

## Update order (after any task)

1. `memory.md`
2. `decisions.md`
3. `data-model.md`, `screens.md`, or `design-tokens.md` — whichever changed
4. `changelog.md`
5. `progress.md`
6. `open-questions.md`

## The rule

A task is **not complete** until its files are updated in the order above. If a file referenced in the read order is missing, create it from its template below before continuing with the task. If a task contradicts `decisions.md`, stop and ask the product owner — do not proceed or silently override.

## Starting a new session

Read `CONTEXT.md` first, every time, before reading anything else or writing any code. It tells you which other files exist, the order to read them in, and the order to update them in once the task is done. Do not skip straight to a task file or to code.

## Templates for missing files

If a file listed above does not exist yet, recreate it with this minimal skeleton, then fill it in as part of the task that needed it.

### `RULES.md`
```markdown
# Rules

Hard constraints every agent must follow. Do not violate without stopping to ask.

- The app never holds money. It only records.
- Entries are never deleted — only voided, with a reason.
- All money arithmetic is done in Kotlin with integer rupees, never floating point.
- Every user-facing string lives in strings.xml for en, hi and gu.
- Colours, radii and blur come only from design-tokens.md.
- Helpers never receive totals.
- Respect the reduce-motion setting and 48 dp touch targets.
- No secrets, keys or passwords in the repository.
```

### `memory.md`
```markdown
# Memory

Durable facts about the user, environment and project that should persist across sessions.

## User
-

## Environment
-

## Project
-
```

### `decisions.md`
```markdown
# Decisions

## YYYY-MM-DD
- Decision made.
- Why.
```

### `changelog.md`
```markdown
# Changelog

## YYYY-MM-DD
- What changed.
- Why.
- Files touched.
- How verified.
```

### `progress.md`
```markdown
# Progress

## Step N.N - <title>
- [ ] Not started / [x] Done (YYYY-MM-DD)

Next step: <description>
```

### `data-model.md`
```markdown
# Data Model

## events/{id}
- members: map of uid -> role (owner, writer, helper, viewer)

### entries (subcollection)
-

### history (subcollection)
-

### boxes (subcollection)
-

## guests/{id}
- Per owner.
```

### `screens.md`
```markdown
# Screens

## <Screen name>
- Purpose:
- States:
- Navigation in/out:
```

### `design-tokens.md`
```markdown
# Design Tokens

Source: Stitch project "Shagun Saathi Android".

## Colours
-

## Radii
-

## Blur
-

## Spacing
-
```

### `open-questions.md`
```markdown
# Open Questions

- Question needing product-owner input.
```
