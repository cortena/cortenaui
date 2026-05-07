package com.cortena.ui.catalog.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cortena.ui.color.ColorToken
import com.cortena.ui.components.Button
import com.cortena.ui.components.ButtonStyle
import com.cortena.ui.components.ButtonVariant
import com.cortena.ui.components.Text
import com.cortena.ui.components.TextRole
import com.cortena.ui.components.Toggle
import com.cortena.ui.theme.LocalColors
import com.cortena.ui.theme.value

@Composable
fun ButtonDemo() {
    val colors = LocalColors.current
    Text("Button", color = Color(colors.primary), role = TextRole.TitleMedium)
    var enable by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Enable Button")
        Toggle(checked = enable, onCheckedChange = { enable = it })
    }
    Text("Button Default")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(enabled = enable, style = ButtonStyle.Primary) { Text("Primary") }
        Button(enabled = enable, style = ButtonStyle.Secondary) { Text("Secondary") }
        Button(enabled = enable, style = ButtonStyle.Accent) { Text("Accent") }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(enabled = enable, style = ButtonStyle.Ghost) { Text("Ghost") }
        Button(enabled = enable, style = ButtonStyle.Destructive) { Text("Destructive") }
    }
    Text("Button Soft Variant")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(enabled = enable, style = ButtonStyle.Primary, variant = ButtonVariant.Soft) {
            Text("Primary")
        }
        Button(enabled = enable, style = ButtonStyle.Secondary, variant = ButtonVariant.Soft) {
            Text("Secondary")
        }
        Button(enabled = enable, style = ButtonStyle.Accent, variant = ButtonVariant.Soft) {
            Text("Accent")
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(enabled = enable, style = ButtonStyle.Ghost, variant = ButtonVariant.Soft) {
            Text("Ghost")
        }
        Button(enabled = enable, style = ButtonStyle.Destructive, variant = ButtonVariant.Soft) {
            Text("Destructive")
        }
    }
    Text("Other")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(enabled = enable, background = ColorToken.Blue500.value()) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite icon",
                tint = ColorToken.Blue50.value(),
            )
            Text("Favorite", color = ColorToken.Blue50.value())
        }
        Button(enabled = enable, background = ColorToken.Green600.value()) { Text("Green") }
        Button(enabled = enable, iconOnly = true, background = ColorToken.Orange500.value()) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add icon",
                tint = Color.White,
            )
        }
        Button(enabled = enable, iconOnly = true, background = ColorToken.Pink500.value()) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit icon",
                tint = Color.White,
            )
        }
    }
}
