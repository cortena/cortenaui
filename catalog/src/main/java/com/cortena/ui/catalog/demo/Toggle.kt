package com.cortena.ui.catalog.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cortena.ui.components.Text
import com.cortena.ui.components.TextRole
import com.cortena.ui.components.Toggle
import com.cortena.ui.theme.LocalColors

@Composable
fun ToggleDemo() {
    val colors = LocalColors.current
    Text("Toggle", color = Color(colors.primary), role = TextRole.TitleMedium)
    var defaultChecked by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Default Toggle")
        Toggle(checked = defaultChecked, onCheckedChange = { defaultChecked = it })
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Disabled Toggle")
        Toggle(
            checked = defaultChecked,
            onCheckedChange = { defaultChecked = it },
            enabled = false,
        )
    }
    var customChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Custom Color")
        Toggle(
            checked = customChecked,
            onCheckedChange = { customChecked = it },
            activeColor = Color(colors.primary),
        )
    }
}
