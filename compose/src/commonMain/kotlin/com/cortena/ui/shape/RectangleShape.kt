package com.cortena.ui.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

@Immutable
data object RectangleShape : ComponentShape {

    override fun corners(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): ComponentShape.Corners {
        return ComponentShape.Corners(
            topLeft = 0f,
            topRight = 0f,
            bottomRight = 0f,
            bottomLeft = 0f,
        )
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
    }

    override fun copy(style: CornerStyle) = this
}
