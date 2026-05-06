# Getting Started with CortenaUI

CortenaUI is a Kotlin Multiplatform design system library specifically designed to build user interfaces for the Cortena operating system (an AOSP-based custom ROM) and its app ecosystem.

## Project Structure

This library is divided into two main layers to ensure platform independence and a strict design language:

1. **`:foundation`**: A pure Kotlin layer with no dependencies on any UI framework. It contains raw design tokens like colors (stored as `Long` ARGB), typography sizes (`Float`), spacing (`Float`), and shape corner radii (`Float`).
2. **`:compose`**: A Jetpack Compose implementation layer that translates tokens from `:foundation` to standard Compose UI objects (like `Color`, `.sp`, `.dp`). There are **no** dependencies on Material / Material3 here, as Cortena builds its own custom design system entirely from scratch.

## Prerequisites

Add the `:compose` module dependency (which internally includes `:foundation`) to the `build.gradle.kts` in your application module:

```kotlin
implementation(project(":compose"))
```

## How to Use

1. Start your user interface at the root of your `Activity` using **`ContentView`**.
2. **`ContentView`** will automatically:
   - Call `enableEdgeToEdge()`.
   - Redraw the status bar color as needed.
   - Enable status bar icons contrast detection.
   - Wrap its content using **`Theme`**.
3. Compose your UI using `Body`, `SafeArea`, `AppBar`, `Text`, and so on.

### Simple Implementation Example (Android)

```kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cortena.ui.components.Text
import com.cortena.ui.layout.AppBar
import com.cortena.ui.layout.Body
import com.cortena.ui.layout.ContentView
import com.cortena.ui.layout.SafeArea
import com.cortena.ui.theme.StatusBarIconMode
import com.cortena.ui.theme.ThemeMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The root of the application, automatically handles Edge-to-Edge and Theme Injector
        ContentView(
            appBar = {
                AppBar(modifier = Modifier.background(Color.DarkGray)) {}
            },
            statusBarColor = Color.DarkGray,
            statusBarIconMode = StatusBarIconMode.Auto, // Will automatically calculate light/dark icons
            themeMode = ThemeMode.Auto
        ) {
            Body(modifier = Modifier.background(Color.Black)) {
                SafeArea {
                    Text(
                        text = "Welcome to Cortena UI!",
                        color = Color.White
                    )
                }
            }
        }
    }
}
```

## Component References

Visit the component guides individually in the `docs/components/` directory:

- [ContentView](components/ContentView.md)
- [Theme](components/Theme.md)
- [Body](components/Body.md)
- [SafeArea](components/SafeArea.md)
- [ScrollView](components/ScrollView.md)
- [AppBar](components/AppBar.md)
- [Text](components/Text.md)
- [Button](components/Button.md)
- [Slider](components/Slider.md)
- [Toggle](components/Toggle.md)
