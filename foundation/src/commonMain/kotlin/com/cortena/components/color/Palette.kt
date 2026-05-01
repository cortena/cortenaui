package com.cortena.components.color

/**
 * Cortena Components — Palette
 *
 * Semantic color roles. Components consume these roles, not raw [ColorTokens]. This decouples
 * components from specific palette values — swapping a theme only requires providing a different
 * [Palette] instance.
 *
 * Each property is a Long (ARGB) to stay framework-agnostic. Compose layer converts these to
 * androidx.compose.ui.graphics.Color.
 */
data class Palette(
    // Surface
    val background: Long,
    val onBackground: Long,
    val surface: Long,
    val onSurface: Long,
    val surfaceVariant: Long,
    val onSurfaceVariant: Long,

    // Primary
    val primary: Long,
    val onPrimary: Long,
    val primaryContainer: Long,
    val onPrimaryContainer: Long,

    // Error
    val error: Long,
    val onError: Long,
    val errorContainer: Long,
    val onErrorContainer: Long,

    // Outline
    val outline: Long,
    val outlineVariant: Long,
)

// BUILT-IN PALETTES
val LightPalette = Palette(
    background = ColorToken.White.resolve(isDark = false),
    onBackground = ColorToken.Gray950.resolve(isDark = false),
    surface = ColorToken.White.resolve(isDark = false),
    onSurface = ColorToken.Gray900.resolve(isDark = false),
    surfaceVariant = ColorToken.Gray50.resolve(isDark = false),
    onSurfaceVariant = ColorToken.Gray950.resolve(isDark = false),
    primary = ColorToken.Blue500.resolve(isDark = false),
    onPrimary = ColorToken.Blue50.resolve(isDark = false),
    primaryContainer = ColorToken.Blue50.resolve(isDark = false),
    onPrimaryContainer = ColorToken.Blue900.resolve(isDark = false),
    error = ColorToken.Red400.resolve(isDark = false),
    onError = ColorToken.Red50.resolve(isDark = false),
    errorContainer = ColorToken.Red50.resolve(isDark = false),
    onErrorContainer = ColorToken.Red900.resolve(isDark = false),
    outline = ColorToken.Gray300.resolve(isDark = false),
    outlineVariant = ColorToken.Gray200.resolve(isDark = false),
)

val DarkPalette = Palette(
    background = ColorToken.Black.resolve(isDark = true),
    onBackground = ColorToken.Gray50.resolve(isDark = true),
    surface = ColorToken.Black.resolve(isDark = true),
    onSurface = ColorToken.Gray100.resolve(isDark = true),
    surfaceVariant = ColorToken.Gray950.resolve(isDark = true),
    onSurfaceVariant = ColorToken.Gray50.resolve(isDark = true),
    primary = ColorToken.Blue500.resolve(isDark = true),
    onPrimary = ColorToken.Blue50.resolve(isDark = true),
    primaryContainer = ColorToken.Blue900.resolve(isDark = true),
    onPrimaryContainer = ColorToken.Blue100.resolve(isDark = true),
    error = ColorToken.Red400.resolve(isDark = true),
    onError = ColorToken.Red50.resolve(isDark = true),
    errorContainer = ColorToken.Red900.resolve(isDark = true),
    onErrorContainer = ColorToken.Red100.resolve(isDark = true),
    outline = ColorToken.Gray600.resolve(isDark = true),
    outlineVariant = ColorToken.Gray700.resolve(isDark = true),
)
