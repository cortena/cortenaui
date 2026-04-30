package com.cortena.components.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance

internal fun interactiveHighlightColor(sourceColor: Color): Color {
    if (!sourceColor.isSpecified) {
        return Color(0xFF8A8A8A)
    }

    val isVeryLight = sourceColor.luminance() > 0.78f
    val targetColor = if (isVeryLight) Color.Black else Color.White
    val fraction = if (isVeryLight) 0.18f else 0.36f
    return lerp(sourceColor, targetColor, fraction)
}
