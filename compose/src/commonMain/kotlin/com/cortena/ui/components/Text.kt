package com.cortena.ui.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.cortena.ui.theme.LocalColors
import com.cortena.ui.theme.LocalContentColor
import com.cortena.ui.theme.LocalTypography
import com.cortena.ui.typography.FontStyle

enum class TextRole {
    DisplayLarge,
    DisplayMedium,
    DisplaySmall,
    HeadlineLarge,
    HeadlineMedium,
    HeadlineSmall,
    TitleLarge,
    TitleMedium,
    TitleSmall,
    BodyLarge,
    BodyMedium,
    BodySmall,
    LabelLarge,
    LabelMedium,
    LabelSmall,
}

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle? = null,
    role: TextRole = TextRole.BodyMedium,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    val colors = LocalColors.current
    val typography = LocalTypography.current

    val localContentColor = LocalContentColor.current
    val resolvedColor =
        when {
            color.isSpecified -> color
            localContentColor != null && localContentColor.isSpecified -> localContentColor
            else -> Color(colors.onBackground)
        }

    val roleStyle =
        when (role) {
            TextRole.DisplayLarge -> typography.displayLarge
            TextRole.DisplayMedium -> typography.displayMedium
            TextRole.DisplaySmall -> typography.displaySmall
            TextRole.HeadlineLarge -> typography.headlineLarge
            TextRole.HeadlineMedium -> typography.headlineMedium
            TextRole.HeadlineSmall -> typography.headlineSmall
            TextRole.TitleLarge -> typography.titleLarge
            TextRole.TitleMedium -> typography.titleMedium
            TextRole.TitleSmall -> typography.titleSmall
            TextRole.BodyLarge -> typography.bodyLarge
            TextRole.BodyMedium -> typography.bodyMedium
            TextRole.BodySmall -> typography.bodySmall
            TextRole.LabelLarge -> typography.labelLarge
            TextRole.LabelMedium -> typography.labelMedium
            TextRole.LabelSmall -> typography.labelSmall
        }

    val resolvedStyle =
        TextStyle(
            fontSize = roleStyle.fontSize.sp,
            lineHeight = roleStyle.lineHeight.sp,
            letterSpacing = roleStyle.letterSpacing.sp,
            fontWeight = FontWeight(roleStyle.fontWeight),
            fontStyle =
                if (roleStyle.fontStyle == FontStyle.Italic)
                    androidx.compose.ui.text.font.FontStyle.Italic
                else androidx.compose.ui.text.font.FontStyle.Normal,
        )
            .merge(style)

    BasicText(
        text = text,
        modifier = modifier,
        style = resolvedStyle.copy(color = resolvedColor),
        maxLines = maxLines,
        overflow = overflow,
    )
}
