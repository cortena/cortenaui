package com.cortena.components.catalog.shape

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import com.cortena.components.shape.CapsuleShape
import com.cortena.components.util.componentBorder

@Composable
fun Slider(
    state: MutableState<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    label: String,
    value: (value: Float) -> String,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    val sliderColor = Color(0xFF0088FF)

    Column(
        modifier
            .componentBorder(1.dp, sliderColor, CapsuleShape())
            .clip(CapsuleShape())
            .drawBehind {
                val value =
                    (state.value - valueRange.start) / (valueRange.endInclusive - valueRange.start)
                drawRect(
                    sliderColor.copy(alpha = 1f / 3f),
                    topLeft =
                        if (layoutDirection == Ltr) Offset.Zero
                        else Offset(size.width * (1f - value), 0f),
                    size = size.copy(width = size.width * value)
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    val delta = dragAmount.x / size.width * if (layoutDirection == Ltr) 1f else -1f
                    state.value =
                        (state.value + delta * (valueRange.endInclusive - valueRange.start)).coerceIn(
                            valueRange
                        )
                }
            }
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        BasicText(
            label,
            style = TextStyle(fontWeight = FontWeight.Medium)
        )
        BasicText(value(state.value))
    }
}