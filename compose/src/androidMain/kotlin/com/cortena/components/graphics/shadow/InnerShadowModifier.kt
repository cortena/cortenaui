package com.cortena.components.graphics.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.cortena.components.graphics.shadow.internal.InnerShadowElement
import com.cortena.components.graphics.shadow.internal.ShapeProvider

actual fun Modifier.componentInnerShadow(
    shadow: InnerShadow,
    shape: Shape,
): Modifier = this then InnerShadowElement(ShapeProvider { shape }) { shadow }
