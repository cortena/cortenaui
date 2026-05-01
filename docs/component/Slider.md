# Slider

`Slider` is Cortena's value adjustment component.

## Concept

The component is a capsule with:

- A filled background segment that represents the current progress.
- A white pill indicator by default, with a subtle shadow for contrast on light surfaces.
- Button-like interactive feedback on the indicator: press glow, drag offset, and stretch.

The component is controlled. You own the state and pass the current value back through `onValueChange`.

## API Reference

```kotlin
@Composable
fun Slider(
    value: () -> Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indicatorColor: Color = Color.Unspecified,
    trackColor: Color = Color.Unspecified,
    progressColor: Color = Color.Unspecified
)
```

### Parameters

| Name             | Data Type                         | Description                                     |
| ---------------- | --------------------------------- | ----------------------------------------------- |
| `value`          | `() -> Float`                     | Current slider value.                           |
| `onValueChange`  | `(Float) -> Unit`                 | Called with a coerced value while dragging.     |
| `valueRange`     | `ClosedFloatingPointRange<Float>` | Minimum and maximum allowed values.             |
| `modifier`       | `Modifier`                        | Standard Compose modifier.                      |
| `enabled`        | `Boolean`                         | Disables drag and gesture effects when `false`. |
| `indicatorColor` | `Color`                           | Indicator pill color. Default: white.           |
| `trackColor`     | `Color`                           | Track color. Default: surface variant.          |
| `progressColor`  | `Color`                           | Filled progress color. Default: primary.        |

### Example

```kotlin
var ratio by rememberSaveable { mutableFloatStateOf(0.5f) }

Slider(
    value = { ratio },
    onValueChange = { ratio = it },
    valueRange = 0f..1f,
)
```

### Custom Colors

```kotlin
var ratio by rememberSaveable { mutableFloatStateOf(0.5f) }

Slider(
    value = { ratio },
    onValueChange = { ratio = it },
    valueRange = 0f..1f,
    indicatorColor = Color.White,
    trackColor = Color(LocalColors.current.surface),
    progressColor = Color(LocalColors.current.primary).copy(alpha = 0.24f),
)
```
