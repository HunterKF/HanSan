package com.jaegerapps.hansan.screens.practice.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnswerContainer(
    modifier: Modifier = Modifier,
    answers: List<String>,
    onSelect: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AnswerItem(
                modifier = Modifier.weight(1f),
                answer = answers[0],
                onSelect = {
                    onSelect(answers[0])
                }
            )
            Spacer(Modifier.width(12.dp))
            AnswerItem(
                modifier = Modifier.weight(1f),
                answer = answers[1],
                onSelect = {
                    onSelect(answers[1])
                }
            )

        }
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AnswerItem(
                modifier = Modifier.weight(1f),
                answer = answers[2],
                onSelect = {
                    onSelect(answers[2])
                }
            )
            Spacer(Modifier.width(12.dp))
            AnswerItem(
                modifier = Modifier.weight(1f),
                answer = answers[3],
                onSelect = {
                    onSelect(answers[3])
                }
            )

        }
    }
}