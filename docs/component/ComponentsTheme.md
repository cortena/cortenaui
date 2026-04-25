# ComponentsTheme

`ComponentsTheme` is the _Root Theming Container_ of the Cortena Components design system. It is the heart that maps the _ThemeMode_ (Dark/Light/Auto) and utilizes the _CompositionLocalProvider_ from Compose to distribute all UI properties to all child composables that use it.

## Concept

Any component written to Cortena specifications, such as `Text` or `SafeArea`, reads its color, spacing, or shape properties via:

- `LocalColors.current`
- `LocalTypography.current`
- `LocalSpacing.current`
- `LocalShapes.current`

The function of `ComponentsTheme` is to provide these actual values at the top of the hierarchy (root node). `ContentView` already calls this internally, so you rarely need to call it directly unless you are inside a preview function (`@Preview`) of pure compose.

## API Reference

```kotlin
@Composable
fun ComponentsTheme(
    themeMode: ThemeMode = ThemeMode.Auto,
    colorScheme: ColorScheme? = null,
    typography: Typography = DefaultTypography,
    content: @Composable () -> Unit
)
```

### Parameters

| Name          | Data Type                | Description                                                                                                                                  |
| ------------- | ------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------- |
| `themeMode`   | `ThemeMode`              | Options are `ThemeMode.Light`, `ThemeMode.Dark`, or `ThemeMode.Auto` (detects current OS mode).                                              |
| `colorScheme` | `ColorScheme?`           | Ignores automatic OS detection and forces the specific color scheme you want. If `null`, it will automatically record following `themeMode`. |
| `typography`  | `Typography`             | Overrides the adjusted _font scale_. Default: `DefaultTypography`.                                                                           |
| `content`     | `@Composable () -> Unit` | Lambda for your child Composable function placed under the umbrella of this theme.                                                           |
