package com.cortena.components.color

// HOLDS A LIGHT + DARK COLOR PAIR AND RESOLVES TO THE CORRECT ONE BASED ON THEME
data class AdaptiveColor(
    val light: Long,
    val dark: Long,
) {
    fun resolve(isDark: Boolean): Long = if (isDark) dark else light
}
