package com.cortena.ui.catalog.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cortena.ui.component.Button
import com.cortena.ui.component.Slider
import com.cortena.ui.component.Text

@Composable
fun SliderDemo() {
    var sliderValue by remember { mutableFloatStateOf(0f) }
    Text("Slider")
    Column {
        Text("value int: ${sliderValue.toInt()}")
        Text("value float: $sliderValue")
    }
    Slider(value = { sliderValue }, onValueChange = { sliderValue = it }, valueRange = -4f..4f)
    var sliderValue2 by remember { mutableFloatStateOf(0f) }
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
    var sliderDiscreteValue by remember { mutableFloatStateOf(0f) }
    Text("Slider Discrete")
    Text("value snapped: $sliderDiscreteValue")
    Slider(
        value = { sliderDiscreteValue },
        onValueChange = { sliderDiscreteValue = it },
        valueRange = -4f..4f,
        steps = 7, // Creates 8 intervals between -4 and 4, meaning a step size of 1.
    )
    var sliderDiscreteDisabledValue by remember { mutableFloatStateOf(0f) }
    Text("Slider Discrete (Disabled)")
    Slider(
        value = { sliderDiscreteDisabledValue },
        onValueChange = { sliderDiscreteDisabledValue = it },
        valueRange = -4f..4f,
        steps = 7,
        enabled = false,
    )
}
