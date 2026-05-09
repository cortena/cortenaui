# ScrollView

`ScrollView` is Cortena's scrollable container with bounce overscroll and an automatic scroll indicator.

!!! warning "Known Limitation With ScrollView"

    Currently, if you wish to use `SafeArea` & `ScrollView` composables together. you need to wrapper the SafeView inside ScrollView,
    otherwise thier are very wired quirks with the placement of scrollbars.

    We know its unintuative and are currently trying to fix it.

    ```kotlin
    ScrollView {
        SafeArea {
            ...
        }
    }
    ```

## Concept

`ScrollView` wraps content in a scrollable region that supports both vertical and horizontal orientations. When the user scrolls past the edges, the content bounces with a spring-back animation instead of the default Android glow/stretch effect.

A thin capsule-shaped scroll indicator appears automatically when content overflows, proportionally sized to the viewport-to-content ratio.

Key behaviors:

- **Bounce overscroll**: content shifts with a 0.3× resistance factor during drag, then springs back on release.
- **Scroll indicator**: auto-sized, auto-positioned, and hidden when content fits without scrolling.
- **Callbacks**: optional listeners for scroll position, top-reached, and bottom-reached events.

## API Reference

```kotlin
@Composable
fun ScrollView(
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Vertical,
    scrollState: ScrollState = rememberScrollState(),
    enabled: Boolean = true,
    reverseLayout: Boolean = false,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    showScrollIndicator: Boolean = true,
    indicatorThickness: Dp = 3.dp,
    indicatorColor: Color = Color.Unspecified,
    indicatorShape: Shape = CapsuleShape(),
    indicatorPadding: Dp = 2.dp,
    indicatorPosition: ScrollIndicatorPosition = ScrollIndicatorPosition.End,
    onScrolled: ((scrollValue: Int, maxScrollValue: Int) -> Unit)? = null,
    onReachedTop: (() -> Unit)? = null,
    onReachedBottom: (() -> Unit)? = null,
    content: @Composable () -> Unit,
)

enum class ScrollIndicatorPosition {
    Start,
    End
}
```

### Parameters

| Name                  | Data Type                 | Description                                                          |
| --------------------- | ------------------------- | -------------------------------------------------------------------- |
| `modifier`            | `Modifier`                | Standard Compose modifier.                                           |
| `orientation`         | `Orientation`             | Scroll direction. Default: `Orientation.Vertical`.                   |
| `scrollState`         | `ScrollState`             | Externally hoisted scroll state. Default: `rememberScrollState()`.   |
| `enabled`             | `Boolean`                 | Enables or disables scrolling. Default: `true`.                      |
| `reverseLayout`       | `Boolean`                 | Reverses the scroll direction. Default: `false`.                     |
| `flingBehavior`       | `FlingBehavior`           | Fling physics. Default: platform default via `ScrollableDefaults`.   |
| `contentPadding`      | `PaddingValues`           | Inner padding applied to the scrollable content. Default: `0.dp`.    |
| `showScrollIndicator` | `Boolean`                 | Shows/hides the scroll indicator. Default: `true`.                   |
| `indicatorThickness`  | `Dp`                      | Thickness of the indicator bar. Default: `3.dp`.                     |
| `indicatorColor`      | `Color`                   | Color of the indicator. Default: `outline` token from `LocalColors`. |
| `indicatorShape`      | `Shape`                   | Shape of the indicator. Default: `CapsuleShape()`.                   |
| `indicatorPadding`    | `Dp`                      | Padding around the indicator. Default: `2.dp`.                       |
| `indicatorPosition`   | `ScrollIndicatorPosition` | Indicator placement (`Start` or `End`). Default: `End`.              |
| `onScrolled`          | `((Int, Int) -> Unit)?`   | Called with `(scrollValue, maxScrollValue)` on every scroll change.  |
| `onReachedTop`        | `(() -> Unit)?`           | Called when scroll position reaches the top (value == 0).            |
| `onReachedBottom`     | `(() -> Unit)?`           | Called when scroll position reaches the bottom (value == maxValue).  |
| `content`             | `@Composable () -> Unit`  | The scrollable content.                                              |

### Example

Basic vertical scroll:

```kotlin
ScrollView(modifier = Modifier.fillMaxSize()) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        repeat(50) { index ->
            Text("Item $index")
        }
    }
}
```

### Horizontal Scroll

```kotlin
ScrollView(
    orientation = Orientation.Horizontal,
    modifier = Modifier.fillMaxWidth(),
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        repeat(20) { index ->
            Box(modifier = Modifier.size(80.dp).background(Color.Gray))
        }
    }
}
```

### Scroll Callbacks

```kotlin
ScrollView(
    modifier = Modifier.fillMaxSize(),
    onScrolled = { value, max -> println("$value / $max") },
    onReachedTop = { println("Reached top") },
    onReachedBottom = { println("Reached bottom") },
) {
    Column {
        repeat(100) { Text("Item $it") }
    }
}
```

### Custom Indicator

```kotlin
ScrollView(
    modifier = Modifier.fillMaxSize(),
    indicatorColor = Color(LocalColors.current.primary),
    indicatorThickness = 4.dp,
    indicatorPosition = ScrollIndicatorPosition.Start,
) {
    Column {
        repeat(50) { Text("Item $it") }
    }
}
```
