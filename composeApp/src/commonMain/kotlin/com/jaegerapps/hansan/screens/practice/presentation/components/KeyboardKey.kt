package com.jaegerapps.hansan.screens.practice.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyboardKey(
    key: String,
    shiftKey: String? = null,
    isShift: Boolean,
    width: Dp = 6.dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .shadow(1.dp, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.background)
            .width(width)
            .heightIn(max = 48.dp)
            .aspectRatio(0.8f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isShift && !shiftKey.isNullOrEmpty()) shiftKey else key,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}