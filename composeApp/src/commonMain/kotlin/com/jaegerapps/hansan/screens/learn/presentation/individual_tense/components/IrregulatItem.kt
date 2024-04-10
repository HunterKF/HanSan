package com.jaegerapps.hansan.screens.learn.presentation.individual_tense.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IrregularItem(
    irregularCharacter: String,
    irregularDescription: String
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = irregularCharacter,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.width(12.dp))
        Text(
            irregularDescription,
            style = MaterialTheme.typography.labelMedium.copy(
                lineBreak = LineBreak.Paragraph,
                lineHeight = 24.sp
            )

        )
    }
}