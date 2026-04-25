package com.cortena.components.layout

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import com.cortena.components.color.ColorScheme
import com.cortena.components.theme.ComponentsTheme
import com.cortena.components.theme.ExperimentalComponentsApi
import com.cortena.components.theme.StatusBarIconMode
import com.cortena.components.theme.ThemeMode
import com.cortena.components.typography.DefaultTypography
import com.cortena.components.typography.Typography

@OptIn(ExperimentalComponentsApi::class)
fun ComponentActivity.ContentView(
    themeMode: ThemeMode = ThemeMode.Auto,
    colorScheme: ColorScheme? = null,
    typography: Typography = DefaultTypography,
    statusBarColor: Color = Color.Transparent,
    statusBarIconMode: StatusBarIconMode = StatusBarIconMode.Auto,
    dynamicColor: Boolean = false,
    appBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    if (dynamicColor) {
        Log.w("ContentView", "dynamicColor is not implemented yet")
    }

    val useDarkIcons =
        when (statusBarIconMode) {
            StatusBarIconMode.Auto -> statusBarColor.luminance() > 0.5f
            StatusBarIconMode.Light -> false
            StatusBarIconMode.Dark -> true
        }

    val statusBarStyle =
        if (useDarkIcons) {
            SystemBarStyle.light(statusBarColor.toArgb(), statusBarColor.toArgb())
        } else {
            SystemBarStyle.dark(statusBarColor.toArgb())
        }

    enableEdgeToEdge(statusBarStyle = statusBarStyle)

    setContent {
        ComponentsTheme(themeMode = themeMode, colorScheme = colorScheme, typography = typography) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    appBar?.invoke()
                    content()
                }

                if (statusBarColor != Color.Transparent) {
                    val density = LocalDensity.current
                    val statusBarHeight =
                        with(density) { WindowInsets.statusBars.getTop(this).toDp() }
                    Box(
                        modifier =
                            Modifier.fillMaxWidth()
                                .height(statusBarHeight)
                                .background(statusBarColor)
                    )
                }
            }
        }
    }
}
