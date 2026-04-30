package com.cortena.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceAtMost
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.cortena.components.shape.CapsuleShape
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.LocalSpacing
import com.cortena.components.util.DampedAnimation
import com.cortena.components.util.InteractiveHighlight
import com.cortena.components.util.inspectDragGestures
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import kotlin.math.tanh

@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indicatorColor: Color = Color.Unspecified,
    containerColor: Color = Color.Unspecified,
    progressColor: Color = Color.Unspecified
) {
    val colors = LocalColors.current
    val spacing = LocalSpacing.current
    val layoutDirection = LocalLayoutDirection.current
    val isLtr = layoutDirection == LayoutDirection.Ltr
    val animationScope = rememberCoroutineScope()
    val interactiveHighlight = remember(animationScope) {
        InteractiveHighlight(animationScope) { size, _ ->
            Offset(size.width * 0.5f, size.height * 0.5f)
        }
    }
    val dampedAnimation = remember(
        animationScope
    ) {
        DampedAnimation(
            animationScope = animationScope,
            initialValue = value.coerceIn(valueRange),
            valueRange = valueRange,
            visibilityThreshold = 0.01f,
            initialScale = 1f,
            pressedScale = 1.3f,
        )
    }

    LaunchedEffect(value, dampedAnimation, valueRange.start, valueRange.endInclusive) {
        val targetValue = value.coerceIn(valueRange)
        if (dampedAnimation.targetValue != targetValue) {
            dampedAnimation.updateValue(targetValue)
        }
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        val shape = CapsuleShape()
        val horizontalIndicatorGap = 0.dp
        val indicatorWidth = 56.dp
        val indicatorHeight = spacing.Xl.dp
        val resolvedContainerColor =
            if (containerColor.isSpecified) containerColor else Color(colors.surfaceVariant)
        val resolvedIndicatorColor =
            if (indicatorColor.isSpecified) indicatorColor else Color.White
        val resolvedProgressColor =
            if (progressColor.isSpecified) progressColor else Color(colors.primary)
        val trackWidth = constraints.maxWidth
        val progress = dampedAnimation.progress.fastCoerceIn(0f, 1f)
        val gestureModifier =
            if (enabled) {
                Modifier.pointerInput(valueRange, layoutDirection, trackWidth) {
                    fun valueFromPosition(x: Float): Float {
                        if (size.width == 0) {
                            return value.coerceIn(valueRange)
                        }
                        val positionProgress =
                            if (isLtr) {
                                x / size.width
                            } else {
                                1f - x / size.width
                            }.fastCoerceIn(0f, 1f)
                        return lerp(valueRange.start, valueRange.endInclusive, positionProgress)
                    }

                    inspectDragGestures(
                        onDragStart = { down ->
                            val targetValue = valueFromPosition(down.position.x)
                            dampedAnimation.press()
                            dampedAnimation.updateValue(targetValue)
                            onValueChange(targetValue)
                        },
                        onDragEnd = {
                            dampedAnimation.release()
                        },
                        onDragCancel = {
                            dampedAnimation.release()
                        }
                    ) { change, _ ->
                        val targetValue = valueFromPosition(change.position.x)
                        dampedAnimation.updateValue(targetValue)
                        onValueChange(targetValue)
                    }
                }
            } else {
                Modifier
            }

        Box(
            Modifier
                .graphicsLayer {
                    if (!enabled) {
                        alpha = 0.38f
                    }
                }
                .clip(shape)
                .background(resolvedContainerColor)
                .drawBehind {
                    drawRect(
                        resolvedProgressColor,
                        topLeft =
                            if (isLtr) {
                                Offset.Zero
                            } else {
                                Offset(size.width * (1f - progress), 0f)
                            },
                        size = size.copy(width = size.width * progress)
                    )
                }
                .then(gestureModifier)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.height(indicatorHeight))
        }

        // Indicator
        Box(
            Modifier
                .align(Alignment.CenterStart)
                .zIndex(1f)
                .graphicsLayer {
                    val gap = horizontalIndicatorGap.toPx()
                    val sliderTranslation =
                        run {
                            val maxTranslation = max(0f, trackWidth - size.width - gap * 2f)
                            val targetProgress = if (isLtr) progress else 1f - progress
                            gap + (maxTranslation * targetProgress).fastCoerceIn(0f, maxTranslation)
                        }

                    val pressProgress = interactiveHighlight.pressProgress
                    val scale = lerp(1f, 1f + 4f.dp.toPx() / size.height, pressProgress)

                    val maxOffset = size.minDimension
                    val initialDerivative = 0.05f
                    val offset = interactiveHighlight.offset
                    translationX =
                        sliderTranslation +
                                maxOffset * tanh(initialDerivative * offset.x / maxOffset)
                    translationY = maxOffset * tanh(initialDerivative * offset.y / maxOffset)

                    val maxDragScale = 4f.dp.toPx() / size.height
                    val offsetAngle = atan2(offset.y, offset.x)
                    scaleX =
                        scale + maxDragScale * abs(cos(offsetAngle) * offset.x / size.maxDimension) *
                                (size.width / size.height).fastCoerceAtMost(1f)
                    scaleY =
                        scale + maxDragScale * abs(sin(offsetAngle) * offset.y / size.maxDimension) *
                                (size.height / size.width).fastCoerceAtMost(1f)

                    scaleX *= dampedAnimation.scaleX
                    scaleY *= dampedAnimation.scaleY
                }
                .clip(shape)
                .background(resolvedIndicatorColor)
                .size(width = indicatorWidth, height = indicatorHeight)
        )
    }
}
