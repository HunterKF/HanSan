package com.jaegerapps.hansan.screens.words.word_list.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaegerapps.hansan.common.components.BottomBarIcon
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.typeToStringResource
import com.jaegerapps.hansan.common.util.BottomBarRouteIcon
import com.jaegerapps.hansan.common.util.Routes
import com.jaegerapps.hansan.screens.learn.presentation.components.FormSelectorItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WordsScreen(
    state: WordUiState,
    onEvent: (WordsUiEvent) -> Unit,
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {
                BottomBarRouteIcon.routeList.forEach {
                    BottomBarIcon(
                        modifier = Modifier.weight(1f),
                        icon = it.icon,
                        text = it.route,
                        selected = it.route == Routes.WORDS,
                        onClick = {
                            onEvent(WordsUiEvent.OnRouteNavigate(it.route))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            val list = listOf(
                ModifierType.VERBS,
                ModifierType.ADJECTIVES,
                ModifierType.ADVERBS
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                list.forEach {
                    val weight by animateFloatAsState(
                        targetValue = if (it == state.wordFilter) 1f else 0.8f,
                        tween()
                    )
                    FormSelectorItem(
                        modifier = Modifier.weight(weight),
                        text = stringResource(typeToStringResource(it)),
                        selected = it == state.wordFilter,
                        onSelect = {
                            onEvent(WordsUiEvent.ChangeType(it))
                        }
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(state.wordList) { index, word ->
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable {
                                onEvent(WordsUiEvent.OnWordNavigate(word.dictionaryWord))
                            },
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = word.dictionaryWord,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = word.definition,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if (state.wordList.lastIndex != index) {
                            HorizontalDivider(Modifier.fillMaxWidth())
                        }
                    }

                }
            }
        }

    }
}