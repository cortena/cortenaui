package com.cortena.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.cortena.components.color.ColorScheme
import com.cortena.components.color.DarkColorScheme
import com.cortena.components.color.LightColorScheme
import com.cortena.components.shape.ShapeTokens
import com.cortena.components.spacing.Spacing
import com.cortena.components.typography.DefaultTypography
import com.cortena.components.typography.Typography

@Composable
fun ComponentsTheme(
    themeMode: ThemeMode = ThemeMode.Auto,
    colorScheme: ColorScheme? = null,
    typography: Typography = DefaultTypography,
    content: @Composable () -> Unit,
) {
    val isDark =
        when (themeMode) {
            ThemeMode.Light -> false
            ThemeMode.Dark -> true
            ThemeMode.Auto -> isSystemInDarkTheme()
        }

    val resolvedColorScheme = colorScheme ?: if (isDark) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalColors provides resolvedColorScheme,
        LocalTypography provides typography,
        LocalSpacing provides Spacing,
        LocalShapes provides ShapeTokens,
        content = content,
    )
}
