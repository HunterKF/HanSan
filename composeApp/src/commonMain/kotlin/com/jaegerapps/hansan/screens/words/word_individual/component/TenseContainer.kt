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
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.common.models.getResStringFromFormality
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TenseContainer(
    modifier: Modifier = Modifier,
    tenseTitle: String,
    tenses: List<WordTenseModel>
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
        tenses.forEach {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(getResStringFromFormality(it.formality)) ,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = it.string,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            if (tenses.last() != it) {
                HorizontalDivider()
            }
        }
    }
}