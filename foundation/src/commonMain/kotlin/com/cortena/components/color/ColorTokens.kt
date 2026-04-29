package com.cortena.components.color

/**
 * Cortena Components — Color Tokens
 *
 * Primitive color palette. These are raw values with no semantic meaning. Do not use these directly
 * in components — use [ColorScheme] instead.
 *
 * Values are stored as Long (ARGB)
 */
object ColorTokens {

    // Template - Light (Temporary delete)
    const val ColorLight50: Long = 0xFF
    const val ColorLight100: Long = 0xFF
    const val ColorLight200: Long = 0xFF
    const val ColorLight300: Long = 0xFF
    const val ColorLight400: Long = 0xFF
    const val ColorLight500: Long = 0xFF
    const val ColorLight600: Long = 0xFF
    const val ColorLight700: Long = 0xFF
    const val ColorLight800: Long = 0xFF
    const val ColorLight900: Long = 0xFF
    const val ColorLight950: Long = 0xFF

    // Template - Dark (Temporary delete)
    const val ColorDark50: Long = 0xFF
    const val ColorDark100: Long = 0xFF
    const val ColorDark200: Long = 0xFF
    const val ColorDark300: Long = 0xFF
    const val ColorDark400: Long = 0xFF
    const val ColorDark500: Long = 0xFF
    const val ColorDark600: Long = 0xFF
    const val ColorDark700: Long = 0xFF
    const val ColorDark800: Long = 0xFF
    const val ColorDark900: Long = 0xFF
    const val ColorDark950: Long = 0xFF

    // Red - Light
    const val RedLight50: Long = 0xFFFFE5E6
    const val RedLight100: Long = 0xFFFFCCCD
    const val RedLight200: Long = 0xFFFF999B
    const val RedLight300: Long = 0xFFFF6B6D
    const val RedLight400: Long = 0xFFFF383B // Base
    const val RedLight500: Long = 0xFFFF0509
    const val RedLight600: Long = 0xFFD10003
    const val RedLight700: Long = 0xFF990002
    const val RedLight800: Long = 0xFF660002
    const val RedLight900: Long = 0xFF330001
    const val RedLight950: Long = 0xFF1A0000

    // Red - Dark
    const val RedDark50: Long = 0xFFFFE5E6
    const val RedDark100: Long = 0xFFFFD1D2
    const val RedDark200: Long = 0xFFFF9EA0
    const val RedDark300: Long = 0xFFFF7072
    const val RedDark400: Long = 0xFFFF4245 // Base
    const val RedDark500: Long = 0xFFFF0A0E
    const val RedDark600: Long = 0xFFD60003
    const val RedDark700: Long = 0xFF9E0003
    const val RedDark800: Long = 0xFF6B0002
    const val RedDark900: Long = 0xFF330001
    const val RedDark950: Long = 0xFF1A0000

    // Orange - Light
    const val OrangeLight50: Long = 0xFFFFF4EB
    const val OrangeLight100: Long = 0xFFFFE9D6
    const val OrangeLight200: Long = 0xFFFFD1A8
    const val OrangeLight300: Long = 0xFFFFBB80
    const val OrangeLight400: Long = 0xFFFFA252
    const val OrangeLight500: Long = 0xFFFF8C28 // Base
    const val OrangeLight600: Long = 0xFFEB6D00
    const val OrangeLight700: Long = 0xFFB35300
    const val OrangeLight800: Long = 0xFF753700
    const val OrangeLight900: Long = 0xFF3D1C00
    const val OrangeLight950: Long = 0xFF1F0E00

    // Orange - Dark

    // Yellow - Light
    // Yellow - Dark

    // Green - Light
    // Green - Dark

    // Mint - Light
    // Mint - Dark

    // Teal - Light
    // Teal - Dark

    // Cyan - Light
    // Cyan - Dark

    // Blue - Light
    const val BlueLight50: Long = 0xFFE5F3FF
    const val BlueLight100: Long = 0xFFCCE7FF
    const val BlueLight200: Long = 0xFF99CFFF
    const val BlueLight300: Long = 0xFF66B8FF
    const val BlueLight400: Long = 0xFF33A0FF
    const val BlueLight500: Long = 0xFF0088FF // Base
    const val BlueLight600: Long = 0xFF006DCC
    const val BlueLight700: Long = 0xFF005299
    const val BlueLight800: Long = 0xFF003666
    const val BlueLight900: Long = 0xFF001B33
    const val BlueLight950: Long = 0xFF000E1A

    // Blue - Dark
    const val BlueDark50: Long = 0xFFE5F4FF
    const val BlueDark100: Long = 0xFFCCE9FF
    const val BlueDark200: Long = 0xFF99D3FF
    const val BlueDark300: Long = 0xFF66BDFF
    const val BlueDark400: Long = 0xFF33A7FF
    const val BlueDark500: Long = 0xFF0091FF // Base
    const val BlueDark600: Long = 0xFF0074CC
    const val BlueDark700: Long = 0xFF005799
    const val BlueDark800: Long = 0xFF003A66
    const val BlueDark900: Long = 0xFF001D33
    const val BlueDark950: Long = 0xFF000E1A

    // Indigo - Light
    // Indigo - Dark

    // Purple - Light
    // Purple - Dark

    // Pink - Light
    // Pink - Dark

    // Brown - Light
    // Brown - Dark

    // Gray - Light
    // Gray - Dark


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
