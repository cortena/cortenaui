package com.cortena.components.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawModifierNode
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.fastCoerceAtMost
import kotlin.math.ceil

fun Modifier.componentBorder(
    width: Dp,
    color: Color,
    shape: Shape
): Modifier =
    this then BorderElement(width, color, shape)

private data class BorderElement(
    val width: Dp,
    val color: Color,
    val shape: Shape
) : ModifierNodeElement<BorderNode>() {

    override fun create() = BorderNode(width, color, shape)

    override fun update(node: BorderNode) {
        node.width = width
        node.color = color
        node.shape = shape
        node.invalidateDrawCache()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "componentBorder"
        properties["width"] = width
        properties["color"] = color
        properties["shape"] = shape
    }
}

private class BorderNode(
    var width: Dp,
    var color: Color,
    var shape: Shape
) : DelegatingNode() {

    override val shouldAutoInvalidate: Boolean = false

    private val drawNode = delegate(CacheDrawModifierNode {
        val minDimension = size.minDimension
        if (width.value < 0f || color.isUnspecified || minDimension == 0f) {
            return@CacheDrawModifierNode onDrawWithContent {
                drawContent()
            }
        }

        val strokeWidthPx =
            if (width == Dp.Hairline) {
                1f
            } else {
                ceil(width.toPx().fastCoerceAtMost(minDimension * 0.5f))
            }
        val borderSize = Size(size.width - strokeWidthPx, size.height - strokeWidthPx)
        val outline = shape.createOutline(borderSize, layoutDirection, this)
        val style = Stroke(strokeWidthPx)

        onDrawWithContent {
            drawContent()

            translate(strokeWidthPx * 0.5f, strokeWidthPx * 0.5f) {
                drawOutline(
                    outline = outline,
                    color = color,
                    style = style
                )
            }
        }
    })

    fun invalidateDrawCache() {
        drawNode.invalidateDrawCache()
    }
}
