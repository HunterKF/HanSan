package com.jaegerapps.hansan.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val DarkColorScheme = darkColorScheme(
    background = backgroundDark,
    onBackground = foregroundDark,
    tertiary = secondaryDark,
    onTertiary = backgroundDark
)

val LightColorScheme = lightColorScheme(
    background = backgroundLight,
    onBackground = foregroundLight,
    tertiary = secondaryLight,
    onTertiary = backgroundLight
)