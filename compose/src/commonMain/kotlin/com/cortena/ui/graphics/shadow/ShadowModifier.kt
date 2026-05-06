package com.cortena.ui.graphics.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

expect fun Modifier.componentShadow(shadow: Shadow, shape: Shape): Modifier
