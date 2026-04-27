# Button

`Button` is Cortena's primary action component. It uses the Cortena theme tokens directly and does not depend on Material3.

## Concept

`Button` renders a capsule-shaped surface with a press highlight and damped drag response. The interaction effect is intentionally shared with other interactive controls such as `Slider`, so touch feedback feels consistent across the design system.

Styles are driven by `LocalColors.current`:

- `Primary`: `primary` background and `onPrimary` content.
- `Secondary`: `primaryContainer` background and `onPrimaryContainer` content.
- `Ghost`: transparent background with an `outline` border.

## API Reference

```kotlin
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.Primary,
    content: @Composable RowScope.() -> Unit
)

enum class ButtonStyle {
    Primary,
    Secondary,
    Ghost
}
```

### Parameters

| Name       | Data Type                         | Description                                      |
| ---------- | --------------------------------- | ------------------------------------------------ |
| `onClick`  | `() -> Unit`                      | Called when the button is clicked.               |
| `modifier` | `Modifier`                        | Standard Compose modifier.                       |
| `enabled`  | `Boolean`                         | Disables click and gesture effects when `false`. |
| `style`    | `ButtonStyle`                     | Visual style of the button.                      |
| `content`  | `@Composable RowScope.() -> Unit` | Row content rendered inside the button.          |

### Example

```kotlin
Button(
    onClick = { /* submit */ },
    style = ButtonStyle.Primary,
) {
    Text("Submit")
}

Button(
    onClick = { /* cancel */ },
    style = ButtonStyle.Ghost,
) {
    Text("Cancel")
}
```
