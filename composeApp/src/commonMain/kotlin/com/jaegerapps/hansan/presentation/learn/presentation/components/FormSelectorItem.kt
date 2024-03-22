package com.jaegerapps.hansan.presentation.learn.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FormSelectorItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
) {
    val color by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary,
        tween(100)
    )

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.2f else 0.8f,
        tween(100))
    Box(
        modifier = modifier.clickable { onSelect() }.padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.scale(scale),
            text = text.replace(" ", "\n"),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            minLines = 2
        )
    }
}