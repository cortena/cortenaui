package com.cortena.components.graphics.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

expect fun Modifier.componentInnerShadow(
    shadow: InnerShadow,
    shape: Shape,
): Modifier
