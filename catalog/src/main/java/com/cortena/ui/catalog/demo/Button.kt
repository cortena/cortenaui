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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cortena.ui.color.ColorToken
import com.cortena.ui.components.Button
import com.cortena.ui.components.ButtonStyle
import com.cortena.ui.components.ButtonVariant
import com.cortena.ui.components.Text
import com.cortena.ui.theme.ThemeMode
import com.cortena.ui.theme.value

@Composable
fun ButtonDemo(themeMode: MutableState<ThemeMode>) {
    Button(
        onLongClick = {
            themeMode.value =
                when (themeMode.value) {
                    ThemeMode.Light -> ThemeMode.Dark
                    ThemeMode.Dark -> ThemeMode.Light
                    ThemeMode.Auto -> ThemeMode.Light
                }
        }
    ) {
        Text(
            when (themeMode.value) {
                ThemeMode.Light -> "Switch to Dark"
                ThemeMode.Dark -> "Switch to Light"
                ThemeMode.Auto -> "Switch to Light"
            }
        )
    }
    Text("Button")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Primary) { Text("Primary") }
        Button(style = ButtonStyle.Secondary) { Text("Secondary") }
        Button(style = ButtonStyle.Accent) { Text("Accent") }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Ghost) { Text("Ghost") }
        Button(style = ButtonStyle.Destructive) { Text("Destructive") }
    }
    Text("Button Soft Variant")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Primary, variant = ButtonVariant.Soft) { Text("Primary") }
        Button(style = ButtonStyle.Secondary, variant = ButtonVariant.Soft) { Text("Secondary") }
        Button(style = ButtonStyle.Accent, variant = ButtonVariant.Soft) { Text("Accent") }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Ghost, variant = ButtonVariant.Soft) { Text("Ghost") }
        Button(style = ButtonStyle.Destructive, variant = ButtonVariant.Soft) {
            Text("Destructive")
        }
    }
    Text("Button Disabled")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Primary, enabled = false) { Text("Primary") }
        Button(style = ButtonStyle.Secondary, enabled = false) { Text("Secondary") }
        Button(style = ButtonStyle.Accent, enabled = false) { Text("Accent") }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Ghost, enabled = false) { Text("Ghost") }
        Button(style = ButtonStyle.Destructive, enabled = false) { Text("Destructive") }
    }
    Text("Button Disabled Soft Variant")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Primary, variant = ButtonVariant.Soft, enabled = false) {
            Text("Primary")
        }
        Button(style = ButtonStyle.Secondary, variant = ButtonVariant.Soft, enabled = false) {
            Text("Secondary")
        }
        Button(style = ButtonStyle.Accent, variant = ButtonVariant.Soft, enabled = false) {
            Text("Accent")
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(style = ButtonStyle.Ghost, variant = ButtonVariant.Soft, enabled = false) {
            Text("Ghost")
        }
        Button(style = ButtonStyle.Destructive, variant = ButtonVariant.Soft, enabled = false) {
            Text("Destructive")
        }
    }
    Text("Other")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(background = ColorToken.Blue500.value()) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite icon",
                tint = ColorToken.Blue50.value(),
            )
            Text("Favorite", color = ColorToken.Blue50.value())
        }
        Button(background = ColorToken.Green600.value()) { Text("Green") }
        Button(iconOnly = true, background = ColorToken.Orange500.value()) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add icon",
                tint = Color.White,
            )
        }
        Button(iconOnly = true, background = ColorToken.Pink500.value()) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit icon",
                tint = Color.White,
            )
        }
    }
}
