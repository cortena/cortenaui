package com.cortena.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceAtMost
import androidx.compose.ui.util.lerp
import com.cortena.components.shape.CapsuleShape
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.LocalContentColor
import com.cortena.components.theme.LocalSpacing
import com.cortena.components.util.InteractiveHighlight
import com.cortena.components.util.componentBorder
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tanh

enum class ButtonStyle { Primary, Secondary, Ghost, Destructive }

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.Primary,
    content: @Composable RowScope.() -> Unit
) {
    val colors = LocalColors.current
    val spacing = LocalSpacing.current
    val animationScope = rememberCoroutineScope()

    val interactiveHighlight = remember(animationScope) {
        InteractiveHighlight(animationScope)
    }

    val backgroundColor = when (style) {
        ButtonStyle.Primary -> Color(colors.primary)
        ButtonStyle.Secondary -> Color(colors.primaryContainer)
        ButtonStyle.Ghost -> Color.Transparent
        ButtonStyle.Destructive -> Color(colors.error)
    }
    val contentColor = when (style) {
        ButtonStyle.Primary -> Color(colors.onPrimary)
        ButtonStyle.Secondary -> Color(colors.onPrimaryContainer)
        ButtonStyle.Ghost -> Color(colors.onBackground)
        ButtonStyle.Destructive -> Color(colors.onError)
    }

    val borderModifier = if (style == ButtonStyle.Ghost) {
        Modifier.componentBorder(1.dp, Color(colors.outline), CapsuleShape())
    } else {
        Modifier
    }

    Row(
        modifier
            .graphicsLayer {
                val progress = interactiveHighlight.pressProgress
                val scale = lerp(1f, 1f + 4f.dp.toPx() / size.height, progress)

                val maxOffset = size.minDimension
                val initialDerivative = 0.05f
                val offset = interactiveHighlight.offset
                translationX = maxOffset * tanh(initialDerivative * offset.x / maxOffset)
                translationY = maxOffset * tanh(initialDerivative * offset.y / maxOffset)

                val maxDragScale = 4f.dp.toPx() / size.height
                val offsetAngle = atan2(offset.y, offset.x)
                scaleX =
                    scale + maxDragScale * abs(cos(offsetAngle) * offset.x / size.maxDimension) *
                            (size.width / size.height).fastCoerceAtMost(1f)
                scaleY =
                    scale + maxDragScale * abs(sin(offsetAngle) * offset.y / size.maxDimension) *
                            (size.height / size.width).fastCoerceAtMost(1f)

                if (!enabled) {
                    alpha = 0.38f
                }
            }
            .clip(CapsuleShape())
            .background(backgroundColor)
            .then(borderModifier)
            .clickable(
                interactionSource = null,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .then(
                if (enabled) {
                    Modifier
                        .then(interactiveHighlight.modifier)
                        .then(interactiveHighlight.gestureModifier)
                } else {
                    Modifier
                }
            )
            .height(48.dp)
            .padding(horizontal = spacing.Md.dp),
        horizontalArrangement = Arrangement.spacedBy(spacing.Sm.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                content()
            }
        }
    )
}
