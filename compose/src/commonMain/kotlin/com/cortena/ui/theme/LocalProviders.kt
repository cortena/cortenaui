/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026-present The CortenaOS Project
 */
package com.cortena.ui.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.cortena.ui.color.LightPalette
import com.cortena.ui.color.Palette
import com.cortena.ui.size.SizeToken
import com.cortena.ui.spacing.Spacing
import com.cortena.ui.typography.DefaultTypography
import com.cortena.ui.typography.Typography

val LocalIsDark: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }
val LocalColors: ProvidableCompositionLocal<Palette> = compositionLocalOf { LightPalette }
val LocalContentColor: ProvidableCompositionLocal<Color?> = compositionLocalOf { null }
val LocalTypography: ProvidableCompositionLocal<Typography> = compositionLocalOf {
    DefaultTypography
}
val LocalFontFamily: ProvidableCompositionLocal<FontFamily> = compositionLocalOf {
    FontFamily.Default
}
val LocalSpacing: ProvidableCompositionLocal<Spacing> = compositionLocalOf { Spacing }
val LocalSizeToken: ProvidableCompositionLocal<SizeToken> = compositionLocalOf { SizeToken.Medium }
