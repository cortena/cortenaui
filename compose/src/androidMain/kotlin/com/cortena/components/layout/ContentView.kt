package com.cortena.components.layout

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

fun ComponentActivity.ContentView(
    appBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    setContent {
        val darkTheme = isSystemInDarkTheme()
        val view = LocalView.current

        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as? Activity)?.window ?: return@SideEffect
                window.isNavigationBarContrastEnforced = false

                // Set edge-to-edge transparent background
                WindowCompat.setDecorFitsSystemWindows(window, false)

                // Adjust icon colors based on dark theme
                val controller = WindowInsetsControllerCompat(window, view)
                controller.isAppearanceLightStatusBars = !darkTheme
                controller.isAppearanceLightNavigationBars = !darkTheme
            }
        }

        Column {
            appBar?.invoke()
            content()
        }
    }
}
