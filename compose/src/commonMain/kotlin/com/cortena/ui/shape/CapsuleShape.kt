package com.cortena.ui.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.cortena.ui.shape.internal.shapeOutline

@Immutable
class CapsuleShape(override val style: CornerStyle = CornerStyle.Continuous) : ComponentShape {

    override fun corners(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): ComponentShape.Corners {
        val radius = size.minDimension * 0.5f
        return ComponentShape.Corners(
            topLeft = radius,
            topRight = radius,
            bottomRight = radius,
            bottomLeft = radius,
        )
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val radius = size.minDimension * 0.5f
        return shapeOutline(size = size, radius = radius, style = style)
    }

    override fun copy(style: CornerStyle) = CapsuleShape(style = style)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CapsuleShape) return false

        if (style != other.style) return false

        return true
    }

    override fun hashCode(): Int {
        return style.hashCode()
    }

    override fun toString(): String {
        return "CapsuleShape(style=$style)"
    }
}
