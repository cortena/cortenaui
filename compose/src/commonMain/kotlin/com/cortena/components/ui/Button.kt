package com.cortena.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.cortena.components.shape.CapsuleShape
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.LocalContentColor
import com.cortena.components.theme.LocalSpacing
import com.cortena.components.util.InteractiveHighlight
import com.cortena.components.util.applyInteractiveAnimation

enum class ButtonStyle { Primary, Secondary, Ghost, Destructive }

@Suppress("ModifierParameter")
@Composable
fun Button(
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactive: Boolean = true,
    style: ButtonStyle = ButtonStyle.Primary,
    background: Color = Color.Unspecified,
    foreground: Color = Color.Unspecified,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = LocalColors.current
    val spacing = LocalSpacing.current
    val animationScope = rememberCoroutineScope()

    val styleBackgroundColor = when (style) {
        ButtonStyle.Primary -> Color(colors.primary)
        ButtonStyle.Secondary -> Color(colors.primaryContainer)
        ButtonStyle.Ghost -> Color(colors.surfaceVariant)
        ButtonStyle.Destructive -> Color(colors.error)
    }
    val styleForegroundColor = when (style) {
        ButtonStyle.Primary -> Color(colors.onPrimary)
        ButtonStyle.Secondary -> Color(colors.onPrimaryContainer)
        ButtonStyle.Ghost -> Color(colors.onSurfaceVariant)
        ButtonStyle.Destructive -> Color(colors.onError)
    }

    val backgroundColor = if (background.isSpecified) background else styleBackgroundColor
    val contentColor = if (foreground.isSpecified) foreground else styleForegroundColor

    // Changes to the background color must not destroy and recreate the InteractiveHighlight
    val interactiveHighlight = remember(animationScope) {
        InteractiveHighlight(
            animationScope = animationScope,
            color = backgroundColor,
        )
    }

    Row(
        modifier = modifier
            .graphicsLayer {
                if (interactive) {
                    applyInteractiveAnimation(
                        pressProgress = interactiveHighlight.pressProgress,
                        offset = interactiveHighlight.offset,
                    )
                }
                alpha = if (enabled) 1f else 0.38f
            }
            .clip(CapsuleShape())
            .background(backgroundColor)
            .then(if (enabled) interactiveHighlight.gestureModifier else Modifier)
            .combinedClickable(
                interactionSource = null,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = { onClick?.invoke() },
                onLongClick = { onLongClick?.invoke() },
            )
            .then(if (enabled) interactiveHighlight.modifier else Modifier)
            .height(48.dp)
            .padding(horizontal = spacing.Md.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = spacing.Sm.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}
