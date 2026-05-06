package com.cortena.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.cortena.ui.color.DarkPalette
import com.cortena.ui.color.LightPalette
import com.cortena.ui.color.Palette
import com.cortena.ui.spacing.Spacing
import com.cortena.ui.typography.DefaultTypography
import com.cortena.ui.typography.Typography

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
        content = content,
    )
}
