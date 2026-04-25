package com.cortena.components.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxWidth().heightIn(min = 128.dp)) {
        content()
    }
}
