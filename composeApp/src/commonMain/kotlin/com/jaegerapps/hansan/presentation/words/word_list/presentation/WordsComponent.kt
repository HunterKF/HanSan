package com.jaegerapps.hansan.presentation.words.word_list.presentation

import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.WordModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class WordsComponent(
    componentContext: ComponentContext,
    words: List<WordModel>,
    private val onNavigate: (String) -> Unit,
    private val onWordNavigate: (String) -> Unit,
) : ComponentContext by componentContext {
    private val _state = MutableStateFlow(WordUiState(
        wordFilter = ModifierType.VERBS,
        wordList = words,
        wordsShown = words.filter { it.type == ModifierType.VERBS }
    ))
    val state = _state.value
    fun onEvent(event: WordsUiEvent) {
        when (event) {
            is WordsUiEvent.OnRouteNavigate -> {
                onNavigate(event.route)
            }

            is WordsUiEvent.OnWordNavigate -> {
                onWordNavigate(event.word)
            }

            is WordsUiEvent.ChangeType -> {
                _state.update {
                    it.copy(
                        wordFilter = event.type,
                        wordsShown = _state.value.wordList.filter { it.type == event.type }
                    )
                }
            }
        }
    }
}