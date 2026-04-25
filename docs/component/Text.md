# Text

`Text` is the fundamental component in Cortena Components to render strings/sentences on the screen. Unlike standard `Text` functions of Jetpack Compose or Material3, _Cortena Text_ is hardwired into the standard typography theme of the Cortena operating system, eliminating the need for developers to depend on the Material Theme.

## Concept

Every text automatically uses base properties from:

1. Default color: `LocalColors.current.onBackground`
2. Default size: `LocalTypography.current.bodyMedium.fontSize.sp`

This ensures that the appearance of all fonts and text colors is uniform and consistent without needing to be reset on every call if you are simply expecting a regular "body sentence" behavior.

## API Reference

```kotlin
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color(LocalColors.current.onBackground),
    fontSize: TextUnit = LocalTypography.current.bodyMedium.fontSize.sp,
    fontFamily: FontFamily? = null,
)
```

### Parameters

| Name         | Data Type     | Description                                                                                      |
| ------------ | ------------- | ------------------------------------------------------------------------------------------------ |
| `text`       | `String`      | The sentence / text you want to render.                                                          |
| `modifier`   | `Modifier`    | Standard Compose modifier.                                                                       |
| `color`      | `Color`       | Text color. The default is a high contrast to your background color (`onBackground`).            |
| `fontSize`   | `TextUnit`    | The _font scale_ size. The default points to `bodyMedium` from Cortena Typography.               |
| `fontFamily` | `FontFamily?` | Changes the _font face_ if needed. If `null`, it uses the built-in OS / Compose runtime default. |

### Example

```kotlin
// Using default style and size
Text(text = "Hello World!")

// Using size and color modifications
Text(
    text = "Large Title",
    color = Color(LocalColors.current.primary),
    fontSize = LocalTypography.current.titleLarge.fontSize.sp
)
```
