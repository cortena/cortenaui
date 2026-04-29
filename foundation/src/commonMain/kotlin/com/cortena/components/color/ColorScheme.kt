package com.cortena.components.color

/**
 * Cortena Components — Color Scheme
 *
 * Semantic color roles. Components consume these roles, not raw [ColorTokens]. This decouples
 * components from specific palette values — swapping a theme only requires providing a different
 * [ColorScheme] instance.
 *
 * Each property is a Long (ARGB) to stay framework-agnostic. Compose layer converts these to
 * androidx.compose.ui.graphics.Color.
 */
data class ColorScheme(
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

// Built-in schemes
val LightColorScheme =
    ColorScheme(
        background = ColorTokens.White,
        onBackground = ColorTokens.Neutral100,
        surface = ColorTokens.White,
        onSurface = ColorTokens.Neutral90,
        surfaceVariant = ColorTokens.Neutral20,
        onSurfaceVariant = ColorTokens.Neutral70,
        primary = ColorTokens.BlueLight500,
        onPrimary = ColorTokens.BlueLight50,
        primaryContainer = ColorTokens.Primary10,
        onPrimaryContainer = ColorTokens.Primary90,
        error = ColorTokens.RedLight400,
        onError = ColorTokens.RedLight50,
        errorContainer = ColorTokens.Error10,
        onErrorContainer = ColorTokens.Error90,
        outline = ColorTokens.Neutral30,
        outlineVariant = ColorTokens.Neutral20,
    )

val DarkColorScheme =
    ColorScheme(
        background = ColorTokens.Black,
        onBackground = ColorTokens.Neutral0,
        surface = ColorTokens.Black,
        onSurface = ColorTokens.Neutral10,
        surfaceVariant = ColorTokens.Neutral80,
        onSurfaceVariant = ColorTokens.Neutral30,
        primary = ColorTokens.BlueDark500,
        onPrimary = ColorTokens.BlueDark50,
        primaryContainer = ColorTokens.Primary90,
        onPrimaryContainer = ColorTokens.Primary10,
        error = ColorTokens.RedDark400,
        onError = ColorTokens.RedDark50,
        errorContainer = ColorTokens.Error90,
        onErrorContainer = ColorTokens.Error10,
        outline = ColorTokens.Neutral60,
        outlineVariant = ColorTokens.Neutral70,
    )
