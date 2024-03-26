package com.jaegerapps.hansan.screens.words.word_individual

sealed interface IndividualWordUiEvent {
    data object OnNavigateBack: IndividualWordUiEvent
}