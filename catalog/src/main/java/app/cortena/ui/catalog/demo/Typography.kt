/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026-present The CortenaOS Project
 */
package app.cortena.ui.catalog.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import framework.cortena.ui.components.Text
import framework.cortena.ui.components.TextRole
import framework.cortena.ui.theme.LocalColors

@Composable
fun TypographyDemo() {
    val colors = LocalColors.current
    Text("Typography", color = Color(colors.primary), role = TextRole.TitleMedium)
    Text("Display", color = Color(colors.secondary), role = TextRole.TitleMedium)
    Column {
        Text("Display Large", role = TextRole.DisplayLarge)
        Text("Display Medium", role = TextRole.DisplayMedium)
        Text("Display Small", role = TextRole.DisplaySmall)
        Text(
            "Display Large Italic",
            role = TextRole.DisplayLarge,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Display Medium Italic",
            role = TextRole.DisplayMedium,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Display Small Italic",
            role = TextRole.DisplaySmall,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
    }
    Text("Headline", color = Color(colors.secondary), role = TextRole.TitleMedium)
    Column {
        Text("Headline Large", role = TextRole.HeadlineLarge)
        Text("Headline Medium", role = TextRole.HeadlineMedium)
        Text("Headline Small", role = TextRole.HeadlineSmall)
        Text(
            "Headline Large Italic",
            role = TextRole.HeadlineLarge,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Headline Medium Italic",
            role = TextRole.HeadlineMedium,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Headline Small Italic",
            role = TextRole.HeadlineSmall,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
    }
    Text("Title", color = Color(colors.secondary), role = TextRole.TitleMedium)
    Column {
        Text("Title Large", role = TextRole.TitleLarge)
        Text("Title Medium", role = TextRole.TitleMedium)
        Text("Title Small", role = TextRole.TitleSmall)
        Text(
            "Title Large Italic",
            role = TextRole.TitleLarge,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Title Medium Italic",
            role = TextRole.TitleMedium,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Title Small Italic",
            role = TextRole.TitleSmall,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
    }
    Text("Body", color = Color(colors.secondary), role = TextRole.TitleMedium)
    Column {
        Text("Body Large", role = TextRole.BodyLarge)
        Text("Body Medium", role = TextRole.BodyMedium)
        Text("Body Small", role = TextRole.BodySmall)
        Text(
            "Body Large Italic",
            role = TextRole.BodyLarge,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Body Medium Italic",
            role = TextRole.BodyMedium,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Body Small Italic",
            role = TextRole.BodySmall,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
    }
    Text("Label", color = Color(colors.secondary), role = TextRole.TitleMedium)
    Column {
        Text("Label Large", role = TextRole.LabelLarge)
        Text("Label Medium", role = TextRole.LabelMedium)
        Text("Label Small", role = TextRole.LabelSmall)
        Text(
            "Label Large Italic",
            role = TextRole.LabelLarge,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Label Medium Italic",
            role = TextRole.LabelMedium,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
        Text(
            "Label Small Italic",
            role = TextRole.LabelSmall,
            style = TextStyle(fontStyle = FontStyle.Italic),
        )
    }
    Text("Advanced Features", color = Color(colors.secondary), role = TextRole.TitleMedium)
    Column {
        Text(
            "Custom Color (Accent Role Color)",
            color = Color(colors.accent),
            role = TextRole.BodyLarge,
        )
        Text(
            "Merged TextStyle (Underlined + Spacing)",
            style = TextStyle(textDecoration = TextDecoration.Underline, letterSpacing = 2.sp),
            role = TextRole.BodyLarge,
            modifier = Modifier.padding(top = 4.dp),
        )
        val loremIpsum =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        Text(
            "Ellipsis Overflow: $loremIpsum",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            role = TextRole.BodyLarge,
            modifier = Modifier.padding(top = 4.dp),
        )
        Text(
            "Ellipsis Overflow 2 Lines: $loremIpsum",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            role = TextRole.BodyLarge,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
