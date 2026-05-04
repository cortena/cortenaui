package com.cortena.components.catalog.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.cortena.components.ui.Button
import com.cortena.components.ui.Slider
import com.cortena.components.ui.Text

@Composable
fun SliderDemo() {
    var sliderValue by rememberSaveable { mutableFloatStateOf(0f) }
    Text("Slider")
    Column {
        Text("value int: ${sliderValue.toInt()}")
        Text("value float: $sliderValue")
    }
    Slider(value = { sliderValue }, onValueChange = { sliderValue = it }, valueRange = -4f..4f)
    var sliderValue2 by rememberSaveable { mutableFloatStateOf(0f) }
    var sliderDisabled by remember { mutableStateOf(false) }
    Text("Slider Disabled")
    Button(
        onClick = {
            sliderDisabled =
                when (sliderDisabled) {
                    true -> false
                    false -> true
                }
        }
    ) {
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
}
