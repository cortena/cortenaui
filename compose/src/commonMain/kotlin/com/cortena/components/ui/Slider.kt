package com.cortena.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.cortena.components.graphics.shadow.Shadow
import com.cortena.components.graphics.shadow.componentShadow
import com.cortena.components.shape.CapsuleShape
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.LocalSpacing
import com.cortena.components.util.DampedAnimation
import com.cortena.components.util.InteractiveHighlight
import com.cortena.components.util.applyInteractiveAnimation
import com.cortena.components.util.inspectDragGestures
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.max

@Composable
fun Slider(
    value: () -> Float,
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

    val shape = CapsuleShape()
    val indicatorWidth = 48.dp
    val indicatorHeight = spacing.Lg.dp
    val resolvedContainerColor =
        if (containerColor.isSpecified) containerColor else Color(colors.surfaceVariant)
    val resolvedIndicatorColor =
        if (indicatorColor.isSpecified) indicatorColor else Color.White
    val resolvedProgressColor =
        if (progressColor.isSpecified) progressColor else Color(colors.primary)
    val indicatorShadow = sliderIndicatorShadow(resolvedContainerColor)
    
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        val trackWidth = constraints.maxWidth
        val layoutDirection = LocalLayoutDirection.current
        val isLtr = layoutDirection == LayoutDirection.Ltr
        val animationScope = rememberCoroutineScope()
        var didDrag by remember { mutableStateOf(false) }
        val dampedAnimation = remember(animationScope) {
            DampedAnimation(
                animationScope = animationScope,
                initialValue = value(),
                valueRange = valueRange,
                visibilityThreshold = 0.01f,
                initialScale = 1f,
                pressedScale = 1.3f,
                onDragStarted = {},
                onDragStopped = {
                    if (didDrag) {
                        onValueChange(targetValue)
                    }
                },
                onDrag = { _, dragAmount ->
                    if (!didDrag) {
                        didDrag = dragAmount.x != 0f
                    }
                    val delta =
                        (valueRange.endInclusive - valueRange.start) * (dragAmount.x / trackWidth)
                    onValueChange(
                        if (isLtr) (targetValue + delta).coerceIn(valueRange)
                        else (targetValue - delta).coerceIn(valueRange)
                    )
                }
            )
        }

        LaunchedEffect(dampedAnimation) {
            snapshotFlow { value() }
                .collectLatest { targetValue ->
                    if (dampedAnimation.targetValue != targetValue) {
                        dampedAnimation.updateValue(targetValue)
                    }
                }
        }

        val interactiveHighlight = remember(animationScope, resolvedIndicatorColor) {
            InteractiveHighlight(
                animationScope = animationScope,
                color = resolvedIndicatorColor,
            ) { size, _ ->
                Offset(size.width * 0.5f, size.height * 0.5f)
            }
        }
        val gestureModifier =
            if (enabled) {
                Modifier.pointerInput(valueRange, layoutDirection, trackWidth) {
                    fun valueFromPosition(x: Float): Float {
                        if (size.width == 0) {
                            return value()
                        }
                        val positionProgress = sliderProgressFromPosition(
                            x = x,
                            trackWidth = size.width.toFloat(),
                            indicatorWidth = indicatorWidth.toPx(),
                            isLtr = isLtr,
                        )
                        return lerp(valueRange.start, valueRange.endInclusive, positionProgress)
                    }
                    inspectDragGestures(
                        onDragStart = { down ->
                            val targetValue = valueFromPosition(down.position.x)
                            dampedAnimation.press()
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
                    val progress = dampedAnimation.progress.fastCoerceIn(0f, 1f)
                    val progressEdge = sliderProgressEdge(
                        trackWidth = size.width,
                        indicatorWidth = indicatorWidth.toPx(),
                        progress = progress,
                        isLtr = isLtr,
                    ).fastCoerceIn(0f, size.width)
                    drawRect(
                        resolvedProgressColor,
                        topLeft =
                            if (isLtr) {
                                Offset.Zero
                            } else {
                                Offset(progressEdge, 0f)
                            },
                        size = size.copy(
                            width =
                                if (isLtr) {
                                    progressEdge
                                } else {
                                    size.width - progressEdge
                                }
                        )
                    )
                }
                .then(gestureModifier)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.height(spacing.Md.dp))
        }

        // Indicator
        Box(
            Modifier
                .zIndex(1f)
                .graphicsLayer {
                    val progress = dampedAnimation.progress.fastCoerceIn(0f, 1f)
                    val sliderTranslation = sliderIndicatorStart(
                        trackWidth = trackWidth.toFloat(),
                        indicatorWidth = size.width,
                        progress = progress,
                        isLtr = isLtr,
                    )
                    applyInteractiveAnimation(
                        pressProgress = interactiveHighlight.pressProgress,
                        offset = interactiveHighlight.offset,
                        baseTranslationX = sliderTranslation,
                        scaleXMultiplier = dampedAnimation.scaleX,
                        scaleYMultiplier = dampedAnimation.scaleY,
                    )
                }
                .componentShadow(indicatorShadow, shape)
                .clip(shape)
                .background(resolvedIndicatorColor)
                .size(width = indicatorWidth, height = indicatorHeight)
        )
    }
}

private fun sliderProgressFromPosition(
    x: Float,
    trackWidth: Float,
    indicatorWidth: Float,
    isLtr: Boolean,
): Float {
    val start = indicatorWidth * 0.5f
    val end = trackWidth - indicatorWidth * 0.5f
    val travel = end - start
    val progress = if (travel > 0f) ((x - start) / travel).fastCoerceIn(0f, 1f) else 0f
    return if (isLtr) progress else 1f - progress
}

private fun sliderIndicatorStart(
    trackWidth: Float,
    indicatorWidth: Float,
    progress: Float,
    isLtr: Boolean,
): Float {
    val availableWidth = max(0f, trackWidth - indicatorWidth)
    val directedProgress = if (isLtr) progress else 1f - progress
    return availableWidth * directedProgress.fastCoerceIn(0f, 1f)
}

private fun sliderProgressEdge(
    trackWidth: Float,
    indicatorWidth: Float,
    progress: Float,
    isLtr: Boolean,
): Float {
    return sliderIndicatorStart(
        trackWidth = trackWidth,
        indicatorWidth = indicatorWidth,
        progress = progress,
        isLtr = isLtr,
    ) + indicatorWidth * 0.5f
}

private fun sliderIndicatorShadow(containerColor: Color): Shadow {
    val isLightContainer = containerColor.luminance() > 0.5f
    return Shadow(
        radius = if (isLightContainer) 12.dp else 8.dp,
        offset = DpOffset(0.dp, if (isLightContainer) 3.dp else 2.dp),
        color = Color.Black.copy(alpha = if (isLightContainer) 0.28f else 0.18f),
    )
}
