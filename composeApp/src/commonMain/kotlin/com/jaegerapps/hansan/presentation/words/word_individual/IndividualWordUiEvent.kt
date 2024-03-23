package com.jaegerapps.hansan.presentation.words.word_individual

sealed interface IndividualWordUiEvent {
    data object OnNavigateBack: IndividualWordUiEvent
}