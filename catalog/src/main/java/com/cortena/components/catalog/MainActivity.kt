package com.cortena.components.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cortena.components.color.ColorToken
import com.cortena.components.layout.AppBar
import com.cortena.components.layout.Body
import com.cortena.components.layout.ContentView
import com.cortena.components.layout.SafeArea
import com.cortena.components.layout.ScrollView
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.StatusBarIconMode
import com.cortena.components.theme.ThemeMode
import com.cortena.components.theme.value
import com.cortena.components.ui.Button
import com.cortena.components.ui.ButtonStyle
import com.cortena.components.ui.Slider
import com.cortena.components.ui.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeMode = mutableStateOf(ThemeMode.Light)
        val statusBarIconMode = mutableStateOf(StatusBarIconMode.Dark)

        ContentView(
            appBar = {
                val colors = LocalColors.current
                AppBar(modifier = Modifier.background(Color(colors.surface))) {}
            },
            themeMode = { themeMode.value },
            statusBarIconMode = { statusBarIconMode.value },
        ) {
            val colors = LocalColors.current
            Body(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(colors.surface))
            ) {
                ScrollView(modifier = Modifier.fillMaxSize()) {
                    SafeArea {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                "Long click to switch ThemeMode",
                                modifier = Modifier.padding(top = 16.dp),
                            )
                            Button(
                                onLongClick = {
                                    themeMode.value = when (themeMode.value) {
                                        ThemeMode.Light -> ThemeMode.Dark
                                        ThemeMode.Dark -> ThemeMode.Light
                                        ThemeMode.Auto -> ThemeMode.Light
                                    }
                                    statusBarIconMode.value = when (statusBarIconMode.value) {
                                        StatusBarIconMode.Light -> StatusBarIconMode.Dark
                                        StatusBarIconMode.Dark -> StatusBarIconMode.Light
                                        StatusBarIconMode.Auto -> StatusBarIconMode.Dark
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Button(style = ButtonStyle.Primary) {
                                    Text("Primary")
                                }
                                Button(style = ButtonStyle.Secondary) {
                                    Text("Secondary")
                                }
                                Button(style = ButtonStyle.Ghost) {
                                    Text("Ghost")
                                }
                                Button(style = ButtonStyle.Destructive) {
                                    Text("Destructive")
                                }
                            }
                            Text("Button Disabled")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Button(
                                    style = ButtonStyle.Primary,
                                    enabled = false
                                ) {
                                    Text("Primary")
                                }
                                Button(
                                    style = ButtonStyle.Secondary,
                                    enabled = false
                                ) {
                                    Text("Secondary")
                                }
                                Button(
                                    style = ButtonStyle.Ghost,
                                    enabled = false
                                ) {
                                    Text("Ghost")
                                }
                                Button(
                                    style = ButtonStyle.Destructive,
                                    enabled = false
                                ) {
                                    Text("Destructive")
                                }
                            }
                            Text("Button Custom")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Button(background = ColorToken.Blue500.value()) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorite icon",
                                        tint = ColorToken.Blue50.value()
                                    )
                                    Text("Favorite", color = ColorToken.Blue50.value())
                                }
                                Button(background = ColorToken.Orange500.value()) {
                                    Text("Orange")
                                }
                                Button(
                                    background = Color(colors.surfaceVariant),
                                    foreground = ColorToken.Red300.value()
                                ) {
                                    Text("Delete")
                                }
                            }
                            var sliderValue by rememberSaveable { mutableFloatStateOf(0f) }
                            Text("Slider (value: ${sliderValue.toInt()})")
                            Slider(
                                value = { sliderValue },
                                onValueChange = { sliderValue = it },
                                valueRange = -4f..4f,
                            )
                            var sliderValue2 by rememberSaveable { mutableFloatStateOf(0f) }
                            var sliderDisabled by remember { mutableStateOf(false) }
                            Text("Slider Disabled")
                            Button(onClick = {
                                sliderDisabled = when (sliderDisabled) {
                                    true -> false
                                    false -> true
                                }
                            }) {
                                Text(
                                    when (sliderDisabled) {
                                        true -> "Disabled"
                                        false -> "Enabled"
                                    }
                                )
                            }
                            Slider(
                                value = { sliderValue2 },
                                onValueChange = { sliderValue2 = it },
                                valueRange = -4f..4f,
                                enabled = sliderDisabled,
                            )
                            Text("Colors")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Red50.value(),
                                    ColorToken.Red100.value(),
                                    ColorToken.Red200.value(),
                                    ColorToken.Red300.value(),
                                    ColorToken.Red400.value(),
                                    ColorToken.Red500.value(),
                                    ColorToken.Red600.value(),
                                    ColorToken.Red700.value(),
                                    ColorToken.Red800.value(),
                                    ColorToken.Red900.value(),
                                    ColorToken.Red950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Orange50.value(),
                                    ColorToken.Orange100.value(),
                                    ColorToken.Orange200.value(),
                                    ColorToken.Orange300.value(),
                                    ColorToken.Orange400.value(),
                                    ColorToken.Orange500.value(),
                                    ColorToken.Orange600.value(),
                                    ColorToken.Orange700.value(),
                                    ColorToken.Orange800.value(),
                                    ColorToken.Orange900.value(),
                                    ColorToken.Orange950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Yellow50.value(),
                                    ColorToken.Yellow100.value(),
                                    ColorToken.Yellow200.value(),
                                    ColorToken.Yellow300.value(),
                                    ColorToken.Yellow400.value(),
                                    ColorToken.Yellow500.value(),
                                    ColorToken.Yellow600.value(),
                                    ColorToken.Yellow700.value(),
                                    ColorToken.Yellow800.value(),
                                    ColorToken.Yellow900.value(),
                                    ColorToken.Yellow950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Green50.value(),
                                    ColorToken.Green100.value(),
                                    ColorToken.Green200.value(),
                                    ColorToken.Green300.value(),
                                    ColorToken.Green400.value(),
                                    ColorToken.Green500.value(),
                                    ColorToken.Green600.value(),
                                    ColorToken.Green700.value(),
                                    ColorToken.Green800.value(),
                                    ColorToken.Green900.value(),
                                    ColorToken.Green950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Mint50.value(),
                                    ColorToken.Mint100.value(),
                                    ColorToken.Mint200.value(),
                                    ColorToken.Mint300.value(),
                                    ColorToken.Mint400.value(),
                                    ColorToken.Mint500.value(),
                                    ColorToken.Mint600.value(),
                                    ColorToken.Mint700.value(),
                                    ColorToken.Mint800.value(),
                                    ColorToken.Mint900.value(),
                                    ColorToken.Mint950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Teal50.value(),
                                    ColorToken.Teal100.value(),
                                    ColorToken.Teal200.value(),
                                    ColorToken.Teal300.value(),
                                    ColorToken.Teal400.value(),
                                    ColorToken.Teal500.value(),
                                    ColorToken.Teal600.value(),
                                    ColorToken.Teal700.value(),
                                    ColorToken.Teal800.value(),
                                    ColorToken.Teal900.value(),
                                    ColorToken.Teal950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Cyan50.value(),
                                    ColorToken.Cyan100.value(),
                                    ColorToken.Cyan200.value(),
                                    ColorToken.Cyan300.value(),
                                    ColorToken.Cyan400.value(),
                                    ColorToken.Cyan500.value(),
                                    ColorToken.Cyan600.value(),
                                    ColorToken.Cyan700.value(),
                                    ColorToken.Cyan800.value(),
                                    ColorToken.Cyan900.value(),
                                    ColorToken.Cyan950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Blue50.value(),
                                    ColorToken.Blue100.value(),
                                    ColorToken.Blue200.value(),
                                    ColorToken.Blue300.value(),
                                    ColorToken.Blue400.value(),
                                    ColorToken.Blue500.value(),
                                    ColorToken.Blue600.value(),
                                    ColorToken.Blue700.value(),
                                    ColorToken.Blue800.value(),
                                    ColorToken.Blue900.value(),
                                    ColorToken.Blue950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Indigo50.value(),
                                    ColorToken.Indigo100.value(),
                                    ColorToken.Indigo200.value(),
                                    ColorToken.Indigo300.value(),
                                    ColorToken.Indigo400.value(),
                                    ColorToken.Indigo500.value(),
                                    ColorToken.Indigo600.value(),
                                    ColorToken.Indigo700.value(),
                                    ColorToken.Indigo800.value(),
                                    ColorToken.Indigo900.value(),
                                    ColorToken.Indigo950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Purple50.value(),
                                    ColorToken.Purple100.value(),
                                    ColorToken.Purple200.value(),
                                    ColorToken.Purple300.value(),
                                    ColorToken.Purple400.value(),
                                    ColorToken.Purple500.value(),
                                    ColorToken.Purple600.value(),
                                    ColorToken.Purple700.value(),
                                    ColorToken.Purple800.value(),
                                    ColorToken.Purple900.value(),
                                    ColorToken.Purple950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Pink50.value(),
                                    ColorToken.Pink100.value(),
                                    ColorToken.Pink200.value(),
                                    ColorToken.Pink300.value(),
                                    ColorToken.Pink400.value(),
                                    ColorToken.Pink500.value(),
                                    ColorToken.Pink600.value(),
                                    ColorToken.Pink700.value(),
                                    ColorToken.Pink800.value(),
                                    ColorToken.Pink900.value(),
                                    ColorToken.Pink950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Brown50.value(),
                                    ColorToken.Brown100.value(),
                                    ColorToken.Brown200.value(),
                                    ColorToken.Brown300.value(),
                                    ColorToken.Brown400.value(),
                                    ColorToken.Brown500.value(),
                                    ColorToken.Brown600.value(),
                                    ColorToken.Brown700.value(),
                                    ColorToken.Brown800.value(),
                                    ColorToken.Brown900.value(),
                                    ColorToken.Brown950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                for (i in arrayOf(
                                    ColorToken.Gray50.value(),
                                    ColorToken.Gray100.value(),
                                    ColorToken.Gray200.value(),
                                    ColorToken.Gray300.value(),
                                    ColorToken.Gray400.value(),
                                    ColorToken.Gray500.value(),
                                    ColorToken.Gray600.value(),
                                    ColorToken.Gray700.value(),
                                    ColorToken.Gray800.value(),
                                    ColorToken.Gray900.value(),
                                    ColorToken.Gray950.value()
                                )) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(i)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}