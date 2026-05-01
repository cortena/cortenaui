package com.cortena.components.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.cortena.components.color.LightPalette
import com.cortena.components.color.Palette
import com.cortena.components.shape.ShapeTokens
import com.cortena.components.spacing.Spacing
import com.cortena.components.typography.DefaultTypography
import com.cortena.components.typography.Typography

val LocalIsDark: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }
val LocalColors: ProvidableCompositionLocal<Palette> = compositionLocalOf { LightPalette }
val LocalContentColor: ProvidableCompositionLocal<Color?> = compositionLocalOf { null }
val LocalTypography: ProvidableCompositionLocal<Typography> = compositionLocalOf {
    DefaultTypography
}
val LocalSpacing: ProvidableCompositionLocal<Spacing> = compositionLocalOf { Spacing }
val LocalShapes: ProvidableCompositionLocal<ShapeTokens> = compositionLocalOf { ShapeTokens }
