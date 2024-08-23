package com.jaegerapps.hansan.screens.words.word_individual.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.getTenseResString
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TenseContainer(
    modifier: Modifier = Modifier,
    tenseTitle: String,
    word: List<Word>
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.tertiary).fillMaxWidth().padding(vertical = 6.dp, horizontal = 12.dp),
        ) {
            Text(
                text = tenseTitle,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        word.forEach {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(getTenseResString(it.tense)) ,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = it.conjugatedWord,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            if (word.last() != it) {
                HorizontalDivider()
            }
        }
    }
}