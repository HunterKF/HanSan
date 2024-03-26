package com.jaegerapps.hansan.screens.words.word_list.presentation

import com.jaegerapps.hansan.common.models.ModifierType

sealed interface WordsUiEvent {
    data class OnRouteNavigate(val route: String): WordsUiEvent
    data class OnWordNavigate(val word: String): WordsUiEvent
    data class ChangeType(val type: ModifierType): WordsUiEvent
}