package com.cortena.ui.color

/**
 * CortenaUI — Palette
 *
 * Semantic color roles. Components consume these roles, not raw [ColorTokens]. This decouples
 * components from specific palette values — swapping a theme only requires providing a different
 * [Palette] instance.
 *
 * Each property is a Long (ARGB) to stay framework-agnostic. Compose layer converts these to
 * androidx.compose.ui.graphics.Color.
 */
data class Palette(
    val primary: Long,
    val onPrimary: Long,
    val primaryContainer: Long,
    val onPrimaryContainer: Long,
    val secondary: Long,
    val onSecondary: Long,
    val secondaryContainer: Long,
    val onSecondaryContainer: Long,
    val accent: Long,
    val onAccent: Long,
    val accentContainer: Long,
    val onAccentContainer: Long,
    val error: Long,
    val onError: Long,
    val errorContainer: Long,
    val onErrorContainer: Long,
    val background: Long,
    val onBackground: Long,
    val surface: Long,
    val onSurface: Long,
    val surfaceVariant: Long,
    val onSurfaceVariant: Long,
    val outline: Long,
    val outlineVariant: Long,
    val scrim: Long,
    val inverseSurface: Long,
    val inverseOnSurface: Long,
    val inversePrimary: Long,
    val surfaceDim: Long,
    val surfaceBright: Long,
    val surfaceContainerLowest: Long,
    val surfaceContainerLow: Long,
    val surfaceContainer: Long,
    val surfaceContainerHigh: Long,
    val surfaceContainerHighest: Long,
)

// Built-in palettes
val LightPalette =
    Palette(
        // Primary — Blue
        primary = ColorToken.Blue500.resolve(isDark = false),
        onPrimary = ColorToken.White.light,
        primaryContainer = ColorToken.Blue100.resolve(isDark = false),
        onPrimaryContainer = ColorToken.Blue800.resolve(isDark = false),
        inversePrimary = ColorToken.Blue300.resolve(isDark = false),

        // Secondary — Gray
        secondary = ColorToken.Gray500.resolve(isDark = false),
        onSecondary = ColorToken.White.light,
        secondaryContainer = ColorToken.Gray100.resolve(isDark = false),
        onSecondaryContainer = ColorToken.Gray800.resolve(isDark = false),

        // Accent — Orange
        accent = ColorToken.Orange500.resolve(isDark = false),
        onAccent = ColorToken.White.light,
        accentContainer = ColorToken.Orange100.resolve(isDark = false),
        onAccentContainer = ColorToken.Orange800.resolve(isDark = false),

        // Error — Red
        error = ColorToken.Red400.resolve(isDark = false),
        onError = ColorToken.White.light,
        errorContainer = ColorToken.Red100.resolve(isDark = false),
        onErrorContainer = ColorToken.Red800.resolve(isDark = false),

        // Background & Surface
        background = ColorToken.White.light,
        onBackground = ColorToken.Gray950.resolve(isDark = false),
        surface = ColorToken.White.light,
        onSurface = ColorToken.Gray900.resolve(isDark = false),

        // surfaceVariant
        surfaceVariant = 0xFFF2F2F7,
        onSurfaceVariant = ColorToken.Gray600.resolve(isDark = false),

        // Surface containers
        surfaceDim = ColorToken.Gray100.resolve(isDark = false),
        surfaceBright = ColorToken.White.light,
        surfaceContainerLowest = ColorToken.White.light,
        surfaceContainerLow = 0xFFF9F9F9,
        surfaceContainer = 0xFFF2F2F7, // grouped background Apple
        surfaceContainerHigh = ColorToken.Gray100.resolve(isDark = false),
        surfaceContainerHighest = ColorToken.Gray200.resolve(isDark = false),

        // Inverse
        inverseSurface = ColorToken.Gray900.resolve(isDark = false),
        inverseOnSurface = ColorToken.Gray100.resolve(isDark = false),

        // Outline
        outline = 0xFFC6C6C8,
        outlineVariant = ColorToken.Gray200.resolve(isDark = false),

        // Scrim
        scrim = ColorToken.Black.light,
    )

val DarkPalette =
    Palette(
        // Primary — Blue
        primary = ColorToken.Blue500.resolve(isDark = true),
        onPrimary = ColorToken.White.dark,
        primaryContainer = ColorToken.Blue900.resolve(isDark = true),
        onPrimaryContainer = ColorToken.Blue200.resolve(isDark = true),
        inversePrimary = ColorToken.Blue600.resolve(isDark = true),

        // Secondary — Gray
        secondary = ColorToken.Gray400.resolve(isDark = true),
        onSecondary = ColorToken.Black.dark,
        secondaryContainer = ColorToken.Gray800.resolve(isDark = true),
        onSecondaryContainer = ColorToken.Gray200.resolve(isDark = true),

        // Accent — Orange
        accent = ColorToken.Orange500.resolve(isDark = true),
        onAccent = ColorToken.Black.dark,
        accentContainer = ColorToken.Orange800.resolve(isDark = true),
        onAccentContainer = ColorToken.Orange200.resolve(isDark = true),

        // Error — Red
        error = ColorToken.Red400.resolve(isDark = true),
        onError = ColorToken.White.dark,
        errorContainer = ColorToken.Red900.resolve(isDark = true),
        onErrorContainer = ColorToken.Red200.resolve(isDark = true),

        // Background & Surface
        background = ColorToken.Black.dark,
        onBackground = ColorToken.Gray50.resolve(isDark = true),
        surface = ColorToken.Black.dark,
        onSurface = ColorToken.Gray100.resolve(isDark = true),

        // surfaceVariant
        surfaceVariant = 0xFF1C1C1E,
        onSurfaceVariant = ColorToken.Gray400.resolve(isDark = true),

        // Surface containers
        surfaceDim = ColorToken.Black.dark,
        surfaceBright = 0xFF2C2C2E,
        surfaceContainerLowest = ColorToken.Black.dark,
        surfaceContainerLow = 0xFF0D0D0D,
        surfaceContainer = 0xFF1C1C1E,
        surfaceContainerHigh = 0xFF2C2C2E,
        surfaceContainerHighest = 0xFF3A3A3C,

        // Inverse
        inverseSurface = ColorToken.Gray100.resolve(isDark = true),
        inverseOnSurface = ColorToken.Gray900.resolve(isDark = true),

        // Outline
        outline = 0xFF38383A,
        outlineVariant = ColorToken.Gray800.resolve(isDark = true),

        // Scrim
        scrim = ColorToken.Black.dark,
    )
