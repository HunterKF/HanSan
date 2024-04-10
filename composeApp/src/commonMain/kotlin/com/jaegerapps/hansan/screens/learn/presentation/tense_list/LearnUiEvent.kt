package com.jaegerapps.hansan.screens.learn.presentation.tense_list

import com.jaegerapps.hansan.common.models.Formality

sealed interface LearnUiEvent {
    data class ChangeFormality(val value: Formality) : LearnUiEvent
    data class OnNavigate(val route: String) : LearnUiEvent
    data class OnNavigateToTense(val tense: String) : LearnUiEvent
}