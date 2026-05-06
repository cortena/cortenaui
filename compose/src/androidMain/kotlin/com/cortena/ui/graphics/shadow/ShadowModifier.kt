package com.cortena.ui.graphics.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.cortena.ui.graphics.shadow.internal.ShadowElement
import com.cortena.ui.graphics.shadow.internal.ShapeProvider

actual fun Modifier.componentShadow(shadow: Shadow, shape: Shape): Modifier =
    this then ShadowElement(ShapeProvider { shape }) { shadow }
