package com.cortena.ui.layout.internal

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import com.cortena.ui.layout.ScrollOrientation
import kotlin.math.abs
import kotlin.math.sign
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
internal class BounceOverscrollEffect(
    private val scope: CoroutineScope,
    private val orientation: ScrollOrientation,
) : OverscrollEffect {

    private val maxOverscroll = 800f
    private val overscrollOffset = Animatable(0f)

    private var snapJob: Job? = null

    init {
        overscrollOffset.updateBounds(-maxOverscroll, maxOverscroll)
    }

    override fun applyToScroll(
        delta: Offset,
        source: NestedScrollSource,
        performScroll: (Offset) -> Offset,
    ): Offset {
        val available = if (orientation == ScrollOrientation.Vertical) delta.y else delta.x
        var consumed = 0f

        // Pre-scroll: Only intercept if dragging in the OPPOSITE direction of the overscroll
        if (overscrollOffset.value != 0f && source != NestedScrollSource.SideEffect) {
            val previousSign = overscrollOffset.value.sign
            val availableSign = available.sign

            if (previousSign * availableSign < 0) {
                val newValue = overscrollOffset.value + available * 0.3f

                val clampedNewValue =
                    when {
                        previousSign > 0 -> newValue.coerceAtLeast(0f)
                        previousSign < 0 -> newValue.coerceAtMost(0f)
                        else -> newValue
                    }

                consumed = (clampedNewValue - overscrollOffset.value) / 0.3f

                snapJob?.cancel()
                snapJob = scope.launch { overscrollOffset.snapTo(clampedNewValue) }
            }
        }

        // Pass the remaining delta to the underlying list/scrollable
        val remainingDelta =
            if (orientation == ScrollOrientation.Vertical) {
                delta.copy(y = delta.y - consumed)
            } else {
                delta.copy(x = delta.x - consumed)
            }

        val scrollConsumed = performScroll(remainingDelta)

        // Post-scroll: If the list couldn't consume all the scroll (hit the bounds), add it to
        // overscroll
        val unconsumed = remainingDelta - scrollConsumed
        val availableUnconsumed =
            if (orientation == ScrollOrientation.Vertical) unconsumed.y else unconsumed.x

        if (abs(availableUnconsumed) > 1f && source == NestedScrollSource.UserInput) {
            val newValue = overscrollOffset.value + availableUnconsumed * 0.3f
            snapJob?.cancel()
            snapJob = scope.launch { overscrollOffset.snapTo(newValue) }
        }

        return if (orientation == ScrollOrientation.Vertical) {
            Offset(scrollConsumed.x, consumed + scrollConsumed.y)
        } else {
            Offset(consumed + scrollConsumed.x, scrollConsumed.y)
        }
    }

    override suspend fun applyToFling(
        velocity: Velocity,
        performFling: suspend (Velocity) -> Velocity,
    ) {
        snapJob?.cancel()
        snapJob = null

        val availableVelocity =
            if (orientation == ScrollOrientation.Vertical) velocity.y else velocity.x
        var consumedVelocity = 0f

        if (overscrollOffset.value != 0f && availableVelocity != 0f) {
            val previousSign = overscrollOffset.value.sign
            val velocitySign = availableVelocity.sign

            if (previousSign * velocitySign < 0) {
                consumedVelocity = availableVelocity

                val predictedEndValue =
                    exponentialDecay<Float>()
                        .calculateTargetValue(
                            initialValue = overscrollOffset.value,
                            initialVelocity = availableVelocity,
                        )

                if (predictedEndValue.sign == previousSign) {
                    overscrollOffset.animateTo(
                        targetValue = 0f,
                        initialVelocity = availableVelocity,
                        animationSpec = spring(stiffness = 300f, dampingRatio = 0.7f),
                    )
                } else {
                    try {
                        overscrollOffset.animateDecay(
                            initialVelocity = availableVelocity,
                            animationSpec = exponentialDecay(),
                        ) {
                            if (value.sign != previousSign) {
                                consumedVelocity -= this.velocity
                                scope.launch { overscrollOffset.snapTo(0f) }
                            }
                        }
                    } catch (_: Exception) {}
                }
            } else {
                scope.launch {
                    overscrollOffset.animateTo(
                        targetValue = 0f,
                        animationSpec = spring(stiffness = 300f, dampingRatio = 0.7f),
                    )
                }
            }
        }

        val remainingVelocity =
            if (orientation == ScrollOrientation.Vertical) {
                velocity.copy(y = velocity.y - consumedVelocity)
            } else {
                velocity.copy(x = velocity.x - consumedVelocity)
            }

        val flingConsumed = performFling(remainingVelocity)

        val unconsumedVelocity = remainingVelocity - flingConsumed
        val postFlingAvailable =
            if (orientation == ScrollOrientation.Vertical) unconsumedVelocity.y
            else unconsumedVelocity.x

        if (postFlingAvailable != 0f) {
            overscrollOffset.animateTo(
                targetValue = 0f,
                initialVelocity = postFlingAvailable,
                animationSpec = spring(stiffness = 300f, dampingRatio = 0.7f),
            )
        } else if (overscrollOffset.value != 0f) {
            overscrollOffset.animateTo(
                targetValue = 0f,
                animationSpec = spring(stiffness = 300f, dampingRatio = 0.7f),
            )
        }
    }

    override val isInProgress: Boolean
        get() = overscrollOffset.value != 0f

    val overscroll: Modifier
        get() =
            Modifier.graphicsLayer {
                if (orientation == ScrollOrientation.Vertical) {
                    translationY = overscrollOffset.value
                } else {
                    translationX = overscrollOffset.value
                }
            }
}
