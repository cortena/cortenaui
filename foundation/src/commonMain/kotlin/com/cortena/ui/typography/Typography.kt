package com.cortena.ui.typography

/**
 * CortenaUI — Typography
 *
 * Semantic typography roles. Maps [TypeScale] values to named roles that components consume.
 * Framework-agnostic data class.
 */
data class TextStyle(val fontSize: Float, val lineHeight: Float, val letterSpacing: Float = 0f)

data class Typography(
    val displayLarge: TextStyle =
        TextStyle(TypeScale.DisplayLarge, TypeScale.LineHeightDisplayLarge),
    val displayMedium: TextStyle =
        TextStyle(TypeScale.DisplayMedium, TypeScale.LineHeightDisplayMedium),
    val displaySmall: TextStyle =
        TextStyle(TypeScale.DisplaySmall, TypeScale.LineHeightDisplaySmall),
    val headlineLarge: TextStyle =
        TextStyle(TypeScale.HeadlineLarge, TypeScale.LineHeightHeadlineLarge),
    val headlineMedium: TextStyle =
        TextStyle(TypeScale.HeadlineMedium, TypeScale.LineHeightHeadlineMedium),
    val headlineSmall: TextStyle =
        TextStyle(TypeScale.HeadlineSmall, TypeScale.LineHeightHeadlineSmall),
    val titleLarge: TextStyle = TextStyle(TypeScale.TitleLarge, TypeScale.LineHeightTitleLarge),
    val titleMedium: TextStyle = TextStyle(TypeScale.TitleMedium, TypeScale.LineHeightTitleMedium),
    val titleSmall: TextStyle = TextStyle(TypeScale.TitleSmall, TypeScale.LineHeightTitleSmall),
    val bodyLarge: TextStyle = TextStyle(TypeScale.BodyLarge, TypeScale.LineHeightBodyLarge),
    val bodyMedium: TextStyle = TextStyle(TypeScale.BodyMedium, TypeScale.LineHeightBodyMedium),
    val bodySmall: TextStyle = TextStyle(TypeScale.BodySmall, TypeScale.LineHeightBodySmall),
    val labelLarge: TextStyle = TextStyle(TypeScale.LabelLarge, TypeScale.LineHeightLabelLarge),
    val labelMedium: TextStyle = TextStyle(TypeScale.LabelMedium, TypeScale.LineHeightLabelMedium),
    val labelSmall: TextStyle = TextStyle(TypeScale.LabelSmall, TypeScale.LineHeightLabelSmall),
)

/** Default typography — override per-role to customize. */
val DefaultTypography = Typography()
