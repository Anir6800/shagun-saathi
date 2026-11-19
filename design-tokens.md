# Design Tokens

Starting-point values only. **Every value below is a seed — replace after Stitch** once the real Stitch project ("Shagun Saathi Android") is connected via the Stitch MCP (D-012 in `decisions.md`). Colours, radii, and blur used in Compose code must come only from this file (`RULES.md`).

## Colour — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Background (dark) | `#0A0A0A` | |
| Background (light) | `#F2F2F2` | |
| Surface | `#161616` | |
| Muted / secondary text | `#8A8A8A` | |

Monochrome only (D-007) — no other colours introduced outside this table until Stitch supplies them.

## Glass — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Glass fill | 8%–14% white | Alpha range over the surface colour. |
| Glass border | 16%–20% white | Alpha range. |

## Typography — seed, replace after Stitch

| Token | Size (sp) | Notes |
|---|---|---|
| Display | 40 | |
| Title | 22 | |
| Body | 16 | |
| Caption | 12 | |

## Spacing — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Base unit | 8dp | 8-point spacing scale (8, 16, 24, 32...). |
| Screen margin | 20dp | |

## Radius — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Corner radius | 20dp | Applies to cards, sheets, glass surfaces. |

## Blur — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Blur radius | 20–30 | Range for glass surfaces. |

## Motion — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Enter | 280ms | Screen/content enter transitions. |
| Stagger | 40ms | Per-item delay in list/stagger animations. |
| Press | 120ms | Press/tap feedback. |
| Count-up | 700ms | Numeric count-up animations (totals, counters). |

All motion uses Compose's `animate*AsState`/`AnimatedVisibility`/`Animatable` APIs and must honour the system reduce-motion setting (`RULES.md`).

## Components — seed, replace after Stitch

| Token | Value | Notes |
|---|---|---|
| Button height | 52dp | Minimum touch target 48dp still applies elsewhere. |
