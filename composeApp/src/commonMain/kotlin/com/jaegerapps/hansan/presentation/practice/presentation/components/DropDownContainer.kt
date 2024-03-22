package com.jaegerapps.hansan.presentation.practice.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropDownContainer(
    modifier: Modifier = Modifier,
    selected: String,
    list: List<String>,
    onExpand: () -> Unit,
    onSelect: (String) -> Unit,
    expanded: Boolean,
) {
    val swivel by animateFloatAsState(
        if (expanded) 180f else 0f,
        tween(300)
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        TextButton(
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = Color.Transparent
            ),
            onClick = {
                onExpand()
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = selected, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.width(6.dp))
                Icon(
                    modifier = Modifier.rotate(swivel),
                    imageVector = Icons.Sharp.ArrowDropDown,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(expanded) {
            Column {
                list.forEach {
                    if (it != selected) {
                        TextButton(
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = Color.Transparent
                            ),
                            onClick = {
                                onSelect(it)
                            }
                        ) {
                            Text(it, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }

}