package com.cortena.components.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cortena.components.color.AdaptiveColor

// AUTO-RESOLVE AN ADAPTIVE COLOR TO THE CORRECT COMPOSE COLOR FOR THE ACTIVE THEME
@Composable
fun AdaptiveColor.value(): Color = Color(resolve(LocalIsDark.current))
