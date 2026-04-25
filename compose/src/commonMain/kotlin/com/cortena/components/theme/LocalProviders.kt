package com.cortena.components.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.cortena.components.color.ColorScheme
import com.cortena.components.color.LightColorScheme
import com.cortena.components.shape.ShapeTokens
import com.cortena.components.spacing.Spacing
import com.cortena.components.typography.DefaultTypography
import com.cortena.components.typography.Typography

val LocalColors: ProvidableCompositionLocal<ColorScheme> = compositionLocalOf { LightColorScheme }
val LocalTypography: ProvidableCompositionLocal<Typography> = compositionLocalOf {
    DefaultTypography
}
val LocalSpacing: ProvidableCompositionLocal<Spacing> = compositionLocalOf { Spacing }
val LocalShapes: ProvidableCompositionLocal<ShapeTokens> = compositionLocalOf { ShapeTokens }
