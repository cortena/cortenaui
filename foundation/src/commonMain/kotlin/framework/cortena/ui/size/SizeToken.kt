/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026-present The CortenaOS Project
 */
package framework.cortena.ui.size

/**
 * CortenaUI — Size Token
 *
 * Defines the five standard component size tiers used throughout CortenaUI. Components read the
 * active [SizeToken] from the theme to determine their height, padding, icon size, and other
 * dimension-related properties.
 *
 * [Medium] is the default tier and matches all existing hardcoded component dimensions, ensuring
 * zero breaking changes for current consumers.
 */
enum class SizeToken {
    ExtraSmall,
    Small,
    Medium,
    Large,
    ExtraLarge,
}
