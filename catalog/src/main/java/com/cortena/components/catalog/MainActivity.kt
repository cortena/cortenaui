package com.cortena.components.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cortena.components.layout.AppBar
import com.cortena.components.layout.Body
import com.cortena.components.layout.ContentView
import com.cortena.components.layout.SafeArea
import com.cortena.components.theme.LocalColors
import com.cortena.components.theme.StatusBarIconMode
import com.cortena.components.ui.Button
import com.cortena.components.ui.ButtonStyle
import com.cortena.components.ui.Slider
import com.cortena.components.ui.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContentView(
            appBar = {
                val colors = LocalColors.current
                AppBar(modifier = Modifier.background(Color(colors.background))) {}
            },
            statusBarIconMode = StatusBarIconMode.Light,
        ) {
            val colors = LocalColors.current
            Body(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(colors.background))
            ) {
                SafeArea {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
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
                        Text("Slider")
                        var sliderValue by remember { mutableFloatStateOf(0f) }
                        Slider(
                            value = sliderValue,
                            onValueChange = { sliderValue = it },
                            valueRange = -4f..4f,
                        )
                    }
                }
            }
        }
    }
}
