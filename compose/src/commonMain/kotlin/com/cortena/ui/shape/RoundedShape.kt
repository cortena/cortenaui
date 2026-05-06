package com.cortena.ui.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.fastCoerceIn
import com.cortena.ui.shape.internal.shapeOutline

@Immutable
class RoundedShape(val cornerRadius: Dp, override val style: CornerStyle = CornerStyle.Continuous) :
    ComponentShape {

    override fun corners(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): ComponentShape.Corners {
        val radius =
            with(density) { cornerRadius.toPx() }.fastCoerceIn(0f, size.minDimension * 0.5f)
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
        val radius =
            with(density) { cornerRadius.toPx() }.fastCoerceIn(0f, size.minDimension * 0.5f)
        return shapeOutline(size = size, radius = radius, style = style)
    }

    override fun copy(style: CornerStyle) = RoundedShape(cornerRadius = cornerRadius, style = style)

    fun copy(cornerRadius: Dp = this.cornerRadius, style: CornerStyle = this.style) =
        RoundedShape(cornerRadius = cornerRadius, style = style)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoundedShape) return false

        if (cornerRadius != other.cornerRadius) return false
        if (style != other.style) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cornerRadius.hashCode()
        result = 31 * result + style.hashCode()
        return result
    }

    override fun toString(): String {
        return "RoundedShape(cornerRadius=$cornerRadius, style=$style)"
    }
}
