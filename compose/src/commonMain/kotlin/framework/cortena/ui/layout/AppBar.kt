/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026-present The CortenaOS Project
 */
package framework.cortena.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import framework.cortena.ui.annotation.ExperimentalComponentsApi

private val APP_BAR_HEIGHT_DEFAULT = 56.dp

@ExperimentalComponentsApi
@Composable
fun AppBar(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .heightIn(min = APP_BAR_HEIGHT_DEFAULT)
    ) {
        content()
    }
}
