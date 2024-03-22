package com.jaegerapps.hansan.core.presentation

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
expect fun HanSanTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
)