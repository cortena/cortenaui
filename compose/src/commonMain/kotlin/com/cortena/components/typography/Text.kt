package com.cortena.components.typography

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.LocalTypography

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color(LocalColors.current.onBackground),
    fontSize: TextUnit = LocalTypography.current.bodyMedium.fontSize.sp,
    fontFamily: FontFamily? = null,
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = TextStyle(color = color, fontSize = fontSize, fontFamily = fontFamily),
    )
}
