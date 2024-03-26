package com.jaegerapps.hansan.screens.words.word_individual

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.screens.words.word_individual.component.ExamineWordContainer
import com.jaegerapps.hansan.screens.words.word_individual.component.TenseContainer
import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.tense_future
import hansan.composeapp.generated.resources.tense_past
import hansan.composeapp.generated.resources.tense_present
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun IndividualWordScreen(
    state: IndividualWordUiState,
    onNavigate: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = {
                        onNavigate()
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "Back",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            var expanded by remember { mutableStateOf(-1) }
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    ExamineWordContainer(
                        modifier = Modifier.padding(12.dp),
                        word = state.currentWord.dictionaryWord,
                        def = state.currentWord.definition
                    )
                }
                item {
                    TenseContainer(
                        modifier = Modifier.fillMaxWidth(),
                        tenseTitle = stringResource(Res.string.tense_present),
                        tenses = state.present
                    )
                }
                item {
                    TenseContainer(
                        modifier = Modifier.fillMaxWidth(),
                        tenseTitle = stringResource(Res.string.tense_past),
                        tenses = state.past
                    )

                }
                item {
                    TenseContainer(
                        modifier = Modifier.fillMaxWidth(),
                        tenseTitle = stringResource(Res.string.tense_future),
                        tenses = state.future
                    )

                }
            }
        }
    }
}