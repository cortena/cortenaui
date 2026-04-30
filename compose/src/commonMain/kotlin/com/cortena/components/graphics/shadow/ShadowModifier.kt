package com.cortena.components.graphics.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

expect fun Modifier.componentShadow(
    shadow: Shadow,
    shape: Shape,
): Modifier
