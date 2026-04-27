# Slider

`Slider` is Cortena's value adjustment component. It keeps the capsule-card form used in the catalog slider, then adds the same press highlight and drag response used by `Button`.

## Concept

The component is a capsule with:

- A border using `LocalColors.current.primary`.
- A filled background segment that represents the current progress.
- A white pill indicator at 85% opacity by default, clamped with a small horizontal gap.
- A label and formatted value inside the capsule.
- Button-like interactive feedback on the indicator: press glow, drag offset, and stretch.

The component is controlled. You own the state and pass the current value back through `onValueChange`.

## API Reference

```kotlin
@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    label: String,
    valueText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indicatorColor: Color = Color.Unspecified,
    borderColor: Color = Color.Unspecified,
    containerColor: Color = Color.Unspecified,
    progressColor: Color = Color.Unspecified
)
```

### Parameters

| Name            | Data Type                         | Description                                      |
| --------------- | --------------------------------- | ------------------------------------------------ |
| `value`         | `Float`                           | Current slider value.                            |
| `onValueChange` | `(Float) -> Unit`                 | Called with a coerced value while dragging.      |
| `valueRange`    | `ClosedFloatingPointRange<Float>` | Minimum and maximum allowed values.              |
| `label`         | `String`                          | Label shown inside the slider.                   |
| `valueText`     | `String`                          | Formatted value shown inside the slider.         |
| `modifier`      | `Modifier`                        | Standard Compose modifier.                       |
| `enabled`       | `Boolean`                         | Disables drag and gesture effects when `false`.  |
| `indicatorColor` | `Color`                          | Indicator pill color. Default: white at 85% alpha. |
| `borderColor`   | `Color`                           | Container border color. Default: theme primary.  |
| `containerColor` | `Color`                          | Capsule background color. Default: background.   |
| `progressColor` | `Color`                           | Filled progress color. Default: primary at 1/3 alpha. |

### Example

```kotlin
var ratio by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = ratio,
    onValueChange = { ratio = it },
    valueRange = 0f..1f,
    label = "Corner radius ratio",
    valueText = "%.3f".format(ratio),
)
```

### Custom Colors

```kotlin
Slider(
    value = ratio,
    onValueChange = { ratio = it },
    valueRange = 0f..1f,
    label = "Corner radius ratio",
    valueText = "%.3f".format(ratio),
    indicatorColor = Color.White,
    borderColor = Color(LocalColors.current.outline),
    containerColor = Color(LocalColors.current.surface),
    progressColor = Color(LocalColors.current.primary).copy(alpha = 0.24f),
)
```

### Catalog Usage

The catalog uses `Slider` in `ShapeCatalog` to adjust aspect ratio and corner radius:

```kotlin
Slider(
    value = cornerRadiusRatio.floatValue,
    onValueChange = { cornerRadiusRatio.floatValue = it },
    valueRange = 0f..1f,
    label = "Corner radius ratio",
    valueText = "%.3f".format(cornerRadiusRatio.floatValue),
)
```
