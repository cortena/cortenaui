package com.cortena.components.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

private val STATUS_BAR_HEIGHT_DEFAULT = 56.dp

@Composable
fun AppBar(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    val statusBarHeight = with(LocalDensity.current) { WindowInsets.statusBars.getTop(this).toDp() }

    Box(
        modifier =
            modifier.fillMaxWidth().heightIn(min = STATUS_BAR_HEIGHT_DEFAULT + statusBarHeight)
    ) {
        content()
    }
}
