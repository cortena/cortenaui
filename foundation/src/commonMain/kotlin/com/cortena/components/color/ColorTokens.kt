package com.cortena.components.color

/**
 * Cortena Components — Color Tokens
 *
 * Primitive color palette. These are raw values with no semantic meaning. Do not use these directly
 * in components — use [ColorScheme] instead.
 *
 * Values are stored as Long (ARGB) so this file has zero dependency on any rendering framework
 * (Compose, View, etc.).
 */
object ColorTokens {

    // Neutral
    const val Black: Long = 0xFF000000
    const val White: Long = 0xFFFFFFFF
    const val Transparent: Long = 0x00000000

    // Neutral scale (light → dark)
    const val Neutral0: Long = 0xFFFFFFFF
    const val Neutral10: Long = 0xFFF5F5F5
    const val Neutral20: Long = 0xFFE5E5E5
    const val Neutral30: Long = 0xFFD4D4D4
    const val Neutral40: Long = 0xFFA3A3A3
    const val Neutral50: Long = 0xFF737373
    const val Neutral60: Long = 0xFF525252
    const val Neutral70: Long = 0xFF404040
    const val Neutral80: Long = 0xFF262626
    const val Neutral90: Long = 0xFF171717
    const val Neutral100: Long = 0xFF000000

    // Primary
    const val Primary10: Long = 0xFFE0F0FF
    const val Primary20: Long = 0xFFBADEFF
    const val Primary30: Long = 0xFF7EC0FF
    const val Primary40: Long = 0xFF3B9EFF
    const val Primary50: Long = 0xFF0A7FFF
    const val Primary60: Long = 0xFF0062D4
    const val Primary70: Long = 0xFF0049A1
    const val Primary80: Long = 0xFF003370
    const val Primary90: Long = 0xFF001E42

    // Error
    const val Error10: Long = 0xFFFFEDED
    const val Error50: Long = 0xFFFF3B30
    const val Error90: Long = 0xFF6B0000
}
