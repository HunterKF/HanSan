package com.jaegerapps.hansan.screens.words.word_list.presentation

import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.VerbModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class WordsComponent(
    componentContext: ComponentContext,
    words: List<VerbModel>,
    private val onNavigate: (String) -> Unit,
    private val onWordNavigate: (String) -> Unit,
) : ComponentContext by componentContext {
    private val _state = MutableStateFlow(WordUiState(
        wordFilter = ModifierType.VERBS,
        wordList = words,
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
                    )
                }
            }
        }
    }
}