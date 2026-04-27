package com.cortena.components.catalog.shape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.cortena.components.shape.CapsuleShape
import com.cortena.components.shape.RectangleShape
import com.cortena.components.shape.lerp
import com.cortena.components.theme.LocalColors
import com.cortena.components.ui.Button
import com.cortena.components.ui.Slider
import com.cortena.components.ui.Text

@Composable
fun ShapeCatalog() {
    val uniformAspectRatio = remember { mutableFloatStateOf(0f) }
    val cornerRadiusRatio = remember { mutableFloatStateOf(0.5f) }
    val colors = LocalColors.current

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier.aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .drawBehind {
                        val shape =
                            lerp(RectangleShape, CapsuleShape(), cornerRadiusRatio.floatValue)
                        drawOutline(
                            shape.createOutline(size, layoutDirection, this),
                            color = Color(colors.primary)
                        )
                    }
                    .layout { measurable, constraints ->
                        val ar = aspectRatio(uniformAspectRatio.floatValue)
                        val width: Int
                        val height: Int
                        if (constraints.maxWidth.toFloat() / constraints.maxHeight.toFloat() >= ar) {
                            height = constraints.maxHeight
                            width = (height.toFloat() * ar).toInt()
                        } else {
                            width = constraints.maxWidth
                            height = (width.toFloat() / ar).toInt()
                        }
                        val placeable = measurable.measure(Constraints.fixed(width, height))
                        layout(width, height) { placeable.place(IntOffset.Zero) }
                    }
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Slider(
                value = uniformAspectRatio.floatValue,
                onValueChange = { uniformAspectRatio.floatValue = it },
                valueRange = -4f..4f,
                label = "Aspect ratio",
                valueText = "%.3f".format(aspectRatio(uniformAspectRatio.floatValue)),
            )
            Slider(
                value = cornerRadiusRatio.floatValue,
                onValueChange = { cornerRadiusRatio.floatValue = it },
                valueRange = 0f..1f,
                label = "Corner radius ratio",
                valueText = "%.3f".format(cornerRadiusRatio.floatValue),
            )
            Button(
                onClick = {
                    uniformAspectRatio.floatValue = 0f
                    cornerRadiusRatio.floatValue = 0.5f
                }
            ) {
                Text("Reset")
            }
        }
    }
}

private fun aspectRatio(value: Float): Float =
    if (value >= 0f) 1f + value else 1f / (1f - value)
