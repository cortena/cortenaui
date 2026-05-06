package com.cortena.ui.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.cortena.ui.color.LightPalette
import com.cortena.ui.color.Palette
import com.cortena.ui.spacing.Spacing
import com.cortena.ui.typography.DefaultTypography
import com.cortena.ui.typography.Typography

val LocalIsDark: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }
val LocalColors: ProvidableCompositionLocal<Palette> = compositionLocalOf { LightPalette }
val LocalContentColor: ProvidableCompositionLocal<Color?> = compositionLocalOf { null }
val LocalTypography: ProvidableCompositionLocal<Typography> = compositionLocalOf {
    DefaultTypography
}
val LocalSpacing: ProvidableCompositionLocal<Spacing> = compositionLocalOf { Spacing }
