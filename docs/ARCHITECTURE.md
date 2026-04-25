# Cortena Components — Architecture

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
foundation/src/commonMain/kotlin/com/cortena/components/
├── color/
│   ├── ColorTokens.kt      # raw ARGB Long values
│   └── ColorScheme.kt      # semantic roles (background, primary, error…)
├── typography/
│   ├── TypeScale.kt        # raw sp Float values
│   └── Typography.kt       # semantic roles (bodyMedium, titleLarge…)
├── spacing/
│   └── Spacing.kt          # 4dp grid (Xs=4, Sm=8, Md=16…)
└── shape/
    └── ShapeTokens.kt      # corner radius (Small=8, Medium=12, Full=9999)
```

### `:compose` (next)

Compose wrappers and theme layer. Depends on `:foundation`.
Converts foundation tokens (Long/Float) to Compose types (Color, TextUnit, Dp).
Provides `CortenaTheme { }` entry point via CompositionLocalProvider.

### `:catalog`

Showcase app. Depends on `:compose` (and transitively `:foundation`).
Used to develop and visually verify components.

### `:view` (future)

Android View system wrappers. Depends on `:foundation`.
Enables ROM legacy code to share the same tokens as Compose components.

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

**Why minSdk 21?**
Library targets should be as broad as possible. The catalog/ROM target is
higher, but the library itself should not artificially restrict consumers.
