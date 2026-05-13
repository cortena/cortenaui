package framework.cortena.ui.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

@Immutable
sealed interface ComponentShape : Shape {

    val style: CornerStyle?
        get() = null

    fun corners(size: Size, layoutDirection: LayoutDirection, density: Density): Corners

    fun copy(style: CornerStyle): ComponentShape

    data class Corners(
        val topLeft: Float,
        val topRight: Float,
        val bottomRight: Float,
        val bottomLeft: Float,
    )
}
