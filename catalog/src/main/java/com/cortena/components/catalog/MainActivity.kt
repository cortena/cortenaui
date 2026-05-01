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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cortena.components.color.ColorTokens
import com.cortena.components.layout.AppBar
import com.cortena.components.layout.Body
import com.cortena.components.layout.ContentView
import com.cortena.components.layout.SafeArea
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.StatusBarIconMode
import com.cortena.components.theme.ThemeMode
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
                SafeArea {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            modifier = Modifier.padding(top = 16.dp),
                            interactive = false,
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
                            Button(
                                onClick = {},
                                style = ButtonStyle.Primary
                            ) {
                                Text("Primary")
                            }
                            Button(
                                onClick = {},
                                style = ButtonStyle.Secondary
                            ) {
                                Text("Secondary")
                            }
                            Button(
                                onClick = {},
                                style = ButtonStyle.Ghost
                            ) {
                                Text("Ghost")
                            }
                            Button(
                                onClick = {},
                                style = ButtonStyle.Destructive
                            ) {
                                Text("Destructive")
                            }
                        }
                        Text("Button Disabled")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {},
                                style = ButtonStyle.Primary,
                                enabled = false
                            ) {
                                Text("Primary")
                            }
                            Button(
                                onClick = {},
                                style = ButtonStyle.Secondary,
                                enabled = false
                            ) {
                                Text("Secondary")
                            }
                            Button(
                                onClick = {},
                                style = ButtonStyle.Ghost,
                                enabled = false
                            ) {
                                Text("Ghost")
                            }
                            Button(
                                onClick = {},
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
                            Button(
                                onClick = {},
                                background = Color(ColorTokens.BlueLight500),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorite icon",
                                    tint = Color(ColorTokens.BlueLight50)
                                )
                                Text("Favorite", color = Color(ColorTokens.BlueLight50))
                            }
                            Button(
                                onClick = {},
                                background = Color(ColorTokens.OrangeLight500),
                            ) {
                                Text("Orange")
                            }
                            Button(
                                onClick = {},
                                background = Color(colors.surfaceVariant),
                                foreground = Color(ColorTokens.RedLight300)
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
                        Text("Colors")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            for (i in arrayOf(
                                ColorTokens.GrayLight50,
                                ColorTokens.GrayLight100,
                                ColorTokens.GrayLight200,
                                ColorTokens.GrayLight300,
                                ColorTokens.GrayLight400,
                                ColorTokens.GrayLight500,
                                ColorTokens.GrayLight600,
                                ColorTokens.GrayLight700,
                                ColorTokens.GrayLight800,
                                ColorTokens.GrayLight900,
                                ColorTokens.GrayLight950
                            )) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(Color(i))
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            for (i in arrayOf(
                                ColorTokens.GrayDark50,
                                ColorTokens.GrayDark100,
                                ColorTokens.GrayDark200,
                                ColorTokens.GrayDark300,
                                ColorTokens.GrayDark400,
                                ColorTokens.GrayDark500,
                                ColorTokens.GrayDark600,
                                ColorTokens.GrayDark700,
                                ColorTokens.GrayDark800,
                                ColorTokens.GrayDark900,
                                ColorTokens.GrayDark950
                            )) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(Color(i))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}