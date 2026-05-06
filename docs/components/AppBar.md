# AppBar

`AppBar` is the top navigation bar or header component in Cortena Components. This component is purely created by detecting _window insets_ (specifically operating system paddings) so it perfectly adapts to the _Edge-to-Edge_ design.

## Concept

Unlike a standard `TopAppBar` which is unaware of insets unless modified, the Cortena `AppBar` proactively reads the OS's `WindowInsets.statusBars` height and adds it to its default size parameter (`STATUS_BAR_HEIGHT_DEFAULT` which is 56dp). This means you simply place your layout inside the AppBar, and it will automatically shift down, safely avoiding the _notch_ (the phone's top camera) overlaps.

## API Reference

```kotlin
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
)
```

### Parameters

| Name       | Data Type                | Description                                                                                                                                                                                                                                  |
| ---------- | ------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `modifier` | `Modifier`               | Standard Compose modifier. Typically filled with modifiers like additional vertical padding or _background_ color. _Important: If providing Modifier.background, the rendered color will also draw into the statusBar boundaries (overlap)_. |
| `content`  | `@Composable () -> Unit` | The content elements of the AppBar, such as _Title_ text, _Back_ button, etc.                                                                                                                                                                |

### Example

```kotlin
AppBar(
    modifier = Modifier.background(Color(LocalColors.current.surface))
) {
    // Insert a Row that renders the title
    SafeArea {
        Text("Settings List")
    }
}
```
