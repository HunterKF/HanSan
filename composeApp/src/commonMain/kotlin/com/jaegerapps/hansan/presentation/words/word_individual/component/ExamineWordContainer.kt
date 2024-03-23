package com.jaegerapps.hansan.presentation.words.word_individual.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExamineWordContainer(
    modifier: Modifier = Modifier,
    word: String,
    def: String
) {
    Column(modifier) {
        Text(
            text = word,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = def,
            style = MaterialTheme.typography.labelMedium
        )
    }
}