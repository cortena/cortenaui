# Architecture

## Module Graph

```
:catalog  ──────────────────────────────────┐
                                            │ depends on
:compose  (coming next) ──── depends on ────►  :foundation
                                            │
:view     (future)      ──── depends on ────┘
```

## Modules

### `:foundation`

**Zero external dependencies. Pure Kotlin.**

The single source of truth for all design tokens. Framework-agnostic by design:
values are primitive types (Long for colors, Float for sizes) so this module
can be consumed by Compose, the Android View system, and AOSP build system
(`Android.bp`) without modification.

```
foundation/src/commonMain/kotlin/com/cortena/ui/
├── color/
│   ├── ColorTokens.kt      # raw ARGB Long values (internal)
│   ├── AdaptiveColor.kt    # light + dark color pair
│   ├── ColorToken.kt       # public adaptive color palette
│   └── Palette.kt          # semantic roles (background, primary, error…)
├── typography/
│   ├── TypeScale.kt        # raw sp Float values
│   └── Typography.kt       # semantic roles (bodyMedium, titleLarge…)
└── spacing/
    └── Spacing.kt          # 4dp grid (Xs=4, Sm=8, Md=16…)
```

### `:compose`

Compose wrappers and theme layer. Depends on `:foundation`.
Converts foundation tokens (`Long`/`Float`) to Compose types (`Color`, `TextUnit`, `Dp`).
Provides `Theme { }` entry point via CompositionLocalProvider.

### `:catalog`

Showcase app. Depends on `:compose` (and transitively `:foundation`).
Used to develop and visually verify components.

## Design Decisions

**Why are colors stored as Long?**
`androidx.compose.ui.graphics.Color` is a Compose type. Storing raw Long in
`:foundation` means the token layer has zero dependency on Compose — it can
be referenced from `Android.bp` builds for ROM integration without pulling
in the entire Compose runtime.

**Why a separate `:compose` module?**
When ROM integration comes, `:foundation` goes into the system image. Compose
components live in apps. Keeping them in separate modules makes that boundary
explicit and enforceable by the build system.
