package framework.cortena.ui.typography

/**
 * CortenaUI — Typography
 *
 * Semantic typography roles. Maps [TypeScale] values to named roles that components consume.
 * Framework-agnostic data class.
 */
data class TextStyle(
    val fontSize: Float,
    val lineHeight: Float,
    val letterSpacing: Float = 0f,
    val fontWeight: Int = 400, // 100–900
    val fontStyle: FontStyle = FontStyle.Normal,
)

enum class FontStyle {
    Normal,
    Italic,
}

data class Typography(
    val displayLarge: TextStyle =
        TextStyle(TypeScale.DisplayLarge, TypeScale.LineHeightDisplayLarge, fontWeight = 700),
    val displayMedium: TextStyle =
        TextStyle(TypeScale.DisplayMedium, TypeScale.LineHeightDisplayMedium, fontWeight = 700),
    val displaySmall: TextStyle =
        TextStyle(TypeScale.DisplaySmall, TypeScale.LineHeightDisplaySmall, fontWeight = 700),
    val headlineLarge: TextStyle =
        TextStyle(TypeScale.HeadlineLarge, TypeScale.LineHeightHeadlineLarge, fontWeight = 600),
    val headlineMedium: TextStyle =
        TextStyle(TypeScale.HeadlineMedium, TypeScale.LineHeightHeadlineMedium, fontWeight = 600),
    val headlineSmall: TextStyle =
        TextStyle(TypeScale.HeadlineSmall, TypeScale.LineHeightHeadlineSmall, fontWeight = 600),
    val titleLarge: TextStyle =
        TextStyle(TypeScale.TitleLarge, TypeScale.LineHeightTitleLarge, fontWeight = 600),
    val titleMedium: TextStyle =
        TextStyle(TypeScale.TitleMedium, TypeScale.LineHeightTitleMedium, fontWeight = 500),
    val titleSmall: TextStyle =
        TextStyle(TypeScale.TitleSmall, TypeScale.LineHeightTitleSmall, fontWeight = 500),
    val bodyLarge: TextStyle = TextStyle(TypeScale.BodyLarge, TypeScale.LineHeightBodyLarge),
    val bodyMedium: TextStyle = TextStyle(TypeScale.BodyMedium, TypeScale.LineHeightBodyMedium),
    val bodySmall: TextStyle = TextStyle(TypeScale.BodySmall, TypeScale.LineHeightBodySmall),
    val labelLarge: TextStyle =
        TextStyle(TypeScale.LabelLarge, TypeScale.LineHeightLabelLarge, fontWeight = 500),
    val labelMedium: TextStyle =
        TextStyle(TypeScale.LabelMedium, TypeScale.LineHeightLabelMedium, fontWeight = 500),
    val labelSmall: TextStyle =
        TextStyle(TypeScale.LabelSmall, TypeScale.LineHeightLabelSmall, fontWeight = 500),
)

/** Default typography — override per-role to customize. */
val DefaultTypography = Typography()
