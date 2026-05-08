package com.cortena.ui.layout

import android.app.Activity
import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.core.view.WindowCompat
import com.cortena.ui.color.Palette
import com.cortena.ui.theme.LocalColors
import com.cortena.ui.theme.StatusBarIconMode
import com.cortena.ui.theme.Theme
import com.cortena.ui.theme.ThemeMode
import com.cortena.ui.typography.DefaultTypography
import com.cortena.ui.typography.Typography

fun ComponentActivity.ContentView(
    // Parameters that can change at runtime → lambda
    themeMode: () -> ThemeMode = { ThemeMode.Auto },
    statusBarIconMode: () -> StatusBarIconMode = { StatusBarIconMode.Auto },
    statusBarColor: () -> Color = { Color.Transparent },

    // Parameters that do not change at runtime → regular values
    palette: Palette? = null,
    typography: Typography = DefaultTypography,
    fontFamily: FontFamily? = null,
    dynamicColor: Boolean = false,

    // Slots
    appBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    if (dynamicColor) {
        Log.w("ContentView", "dynamicColor is not implemented yet")
    }

    enableEdgeToEdge()

    setContent {
        // All resolves within setContent — reactive to recompose
        val currentThemeMode = themeMode()
        val currentStatusBarColor = statusBarColor()
        val currentStatusBarIconMode = statusBarIconMode()

        Theme(
            themeMode = currentThemeMode,
            palette = palette,
            typography = typography,
            fontFamily = fontFamily,
        ) {
            val colors = LocalColors.current
            val useDarkIcons =
                when (currentStatusBarIconMode) {
                    StatusBarIconMode.Auto -> {
                        val referenceColor =
                            if (currentStatusBarColor == Color.Transparent) {
                                Color(colors.surface)
                            } else {
                                currentStatusBarColor
                            }
                        referenceColor.luminance() > 0.5f
                    }

                    StatusBarIconMode.Light -> false
                    StatusBarIconMode.Dark -> true
                }

            val view = LocalView.current
            SideEffect {
                val window = (view.context as Activity).window
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    useDarkIcons
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    appBar?.invoke()
                    content()
                }
                if (currentStatusBarColor != Color.Transparent) {
                    val statusBarHeight =
                        with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() }
                    Box(
                        modifier =
                            Modifier.fillMaxWidth()
                                .height(statusBarHeight)
                                .background(currentStatusBarColor)
                    )
                }
            }
        }
    }
}
