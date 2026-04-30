# Button

`Button` is Cortena's primary action component.

## Concept

`Button` renders a capsule-shaped surface with an automatic tonal press highlight and damped drag response.

Styles are driven by `LocalColors.current`:

- `Primary`: `primary` background and `onPrimary` content.
- `Secondary`: `primaryContainer` background and `onPrimaryContainer` content.
- `Ghost`: `surfaceVariant` background and `onSurfaceVariant` content.
- `Destructive`: `error` background and `onError` content.

## API Reference

```kotlin
@Composable
fun Button(
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactive: Boolean = true,
    style: ButtonStyle = ButtonStyle.Primary,
    background: Color = Color.Unspecified,
    foreground: Color = Color.Unspecified,
    content: @Composable RowScope.() -> Unit
)

enum class ButtonStyle {
    Primary,
    Secondary,
    Ghost,
    Destructive
}
```

### Parameters

| Name          | Data Type                         | Description                                                                           |
| ------------- | --------------------------------- | ------------------------------------------------------------------------------------- |
| `onClick`     | `(() -> Unit)? = null`            | Called when the button is clicked.                                                    |
| `onLongClick` | `(() -> Unit)? = null`            | Called when the button is long clicked.                                               |
| `modifier`    | `Modifier`                        | Standard Compose modifier.                                                            |
| `enabled`     | `Boolean`                         | Disables click and gesture effects when `false`.                                      |
| `interactive` | `Boolean`                         | Enables press/drag motion animation when `true`; the press highlight remains enabled. |
| `style`       | `ButtonStyle`                     | Visual style of the button.                                                           |
| `background`  | `Color.Unspecified`               | Background color of the button.                                                       |
| `foreground`  | `Color.Unspecified`               | Foreground color of the button.                                                       |
| `content`     | `@Composable RowScope.() -> Unit` | Row content rendered inside the button.                                               |

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
    style = ButtonStyle.Destructive,
) {
    Text("Cancel")
}
```
