package com.cortena.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.cortena.components.color.DarkPalette
import com.cortena.components.color.LightPalette
import com.cortena.components.color.Palette
import com.cortena.components.shape.ShapeTokens
import com.cortena.components.spacing.Spacing
import com.cortena.components.typography.DefaultTypography
import com.cortena.components.typography.Typography

@Composable
fun ComponentsTheme(
    themeMode: ThemeMode = ThemeMode.Auto,
    palette: Palette? = null,
    typography: Typography = DefaultTypography,
    content: @Composable () -> Unit,
) {
    val isDark =
        when (themeMode) {
            ThemeMode.Light -> false
            ThemeMode.Dark -> true
            ThemeMode.Auto -> isSystemInDarkTheme()
        }

    val resolvedPalette = palette ?: if (isDark) DarkPalette else LightPalette

    CompositionLocalProvider(
        LocalIsDark provides isDark,
        LocalColors provides resolvedPalette,
        LocalTypography provides typography,
        LocalSpacing provides Spacing,
        LocalShapes provides ShapeTokens,
        content = content,
    )
}
