/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026-present The CortenaOS Project
 */
package com.cortena.ui.layout

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cortena.ui.geometry.Orientation
import com.cortena.ui.layout.internal.BounceOverscrollEffect
import com.cortena.ui.shape.CapsuleShape
import com.cortena.ui.theme.LocalColors

enum class ScrollIndicatorPosition {
    Start,
    End,
}

@Composable
fun ScrollView(
    modifier: Modifier = Modifier,

    // Orientation
    orientation: Orientation = Orientation.Vertical,

    // Scroll Control
    scrollState: ScrollState = rememberScrollState(),
    enabled: Boolean = true,
    reverseLayout: Boolean = false,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),

    // Content Padding
    contentPadding: PaddingValues = PaddingValues(0.dp),

    // Scroll Indicator
    showScrollIndicator: Boolean = true,
    indicatorThickness: Dp = 3.dp,
    indicatorColor: Color = Color.Unspecified,
    indicatorShape: Shape = CapsuleShape(),
    indicatorPadding: Dp = 2.dp,
    indicatorPosition: ScrollIndicatorPosition = ScrollIndicatorPosition.End,

    // Callbacks
    onScrolled: ((scrollValue: Int, maxScrollValue: Int) -> Unit)? = null,
    onReachedTop: (() -> Unit)? = null,
    onReachedBottom: (() -> Unit)? = null,

    // Sticky Header
    // TODO: Implement sticky header for ScrollView
    // stickyHeader: (@Composable () -> Unit)? = null,

    content: @Composable () -> Unit,
) {
    val safeModifier =
        modifier.then(
            if (orientation == Orientation.Vertical) {
                Modifier.heightIn(min = 48.dp) // minimum sensible height
            } else {
                Modifier.widthIn(min = 48.dp)
            }
        )

    val colors = LocalColors.current
    val resolvedIndicatorColor =
        if (indicatorColor.isSpecified) indicatorColor else Color(colors.outline)

    // Bounce Overscroll
    val bounceScope = rememberCoroutineScope()
    val overscrollEffect =
        remember(bounceScope, orientation) { BounceOverscrollEffect(bounceScope, orientation) }

    // Callbacks via SnapshotFlow
    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value }
            .collect { value ->
                onScrolled?.invoke(value, scrollState.maxValue)
                if (value == 0) onReachedTop?.invoke()
                if (value == scrollState.maxValue && scrollState.maxValue > 0) {
                    onReachedBottom?.invoke()
                }
            }
    }

    // Layout
    Box(modifier = safeModifier) {
        val scrollModifier =
            if (orientation == Orientation.Vertical) {
                Modifier.verticalScroll(
                    state = scrollState,
                    enabled = enabled,
                    flingBehavior = flingBehavior,
                    reverseScrolling = reverseLayout,
                    overscrollEffect = overscrollEffect,
                )
            } else {
                Modifier.horizontalScroll(
                    state = scrollState,
                    enabled = enabled,
                    flingBehavior = flingBehavior,
                    reverseScrolling = reverseLayout,
                    overscrollEffect = overscrollEffect,
                )
            }

        if (orientation == Orientation.Vertical) {
            Column(
                modifier = scrollModifier.then(overscrollEffect.overscroll).padding(contentPadding)
            ) {
                content()
            }
        } else {
            Row(
                modifier = scrollModifier.then(overscrollEffect.overscroll).padding(contentPadding)
            ) {
                content()
            }
        }

        // Scroll Indicator
        if (showScrollIndicator && scrollState.maxValue > 0) {
            ScrollIndicator(
                scrollState = scrollState,
                orientation = orientation,
                thickness = indicatorThickness,
                color = resolvedIndicatorColor,
                shape = indicatorShape,
                padding = indicatorPadding,
                position = indicatorPosition,
            )
        }
    }
}

@Suppress("FrequentlyChangingValue")
@Composable
private fun ScrollIndicator(
    scrollState: ScrollState,
    orientation: Orientation,
    thickness: Dp,
    color: Color,
    shape: Shape,
    padding: Dp,
    position: ScrollIndicatorPosition,
) {
    val density = LocalDensity.current
    val minIndicatorSize = with(density) { 48.dp.toPx() }
    val viewportSize = scrollState.viewportSize.toFloat()
    val totalSize = viewportSize + scrollState.maxValue
    val indicatorRatio = if (totalSize > 0f) viewportSize / totalSize else 1f

    val alignment =
        when (orientation) {
            Orientation.Vertical ->
                when (position) {
                    ScrollIndicatorPosition.End -> Alignment.TopEnd
                    ScrollIndicatorPosition.Start -> Alignment.TopStart
                }

            Orientation.Horizontal ->
                when (position) {
                    ScrollIndicatorPosition.End -> Alignment.BottomStart
                    ScrollIndicatorPosition.Start -> Alignment.TopStart
                }
        }

    val scrollFraction =
        if (scrollState.maxValue > 0) {
            scrollState.value.toFloat() / scrollState.maxValue
        } else {
            0f
        }

    val indicatorModifier =
        if (orientation == Orientation.Vertical) {
            val indicatorHeight = (viewportSize * indicatorRatio).coerceAtLeast(minIndicatorSize)
            val trackSize = viewportSize - indicatorHeight
            val indicatorOffset = scrollFraction * trackSize

            Modifier.padding(padding)
                .width(thickness)
                .height(with(density) { indicatorHeight.toDp() })
                .graphicsLayer { translationY = indicatorOffset }
        } else {
            val indicatorWidth = (viewportSize * indicatorRatio).coerceAtLeast(minIndicatorSize)
            val indicatorOffset = scrollFraction * (viewportSize - indicatorWidth)

            Modifier.padding(padding)
                .height(thickness)
                .width(with(density) { indicatorWidth.toDp() })
                .graphicsLayer { translationX = indicatorOffset }
        }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = alignment) {
        Box(modifier = indicatorModifier.clip(shape).background(color, shape))
    }
}
