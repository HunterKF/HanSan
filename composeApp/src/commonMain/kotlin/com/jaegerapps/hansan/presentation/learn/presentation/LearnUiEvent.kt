package com.jaegerapps.hansan.presentation.learn.presentation

import com.jaegerapps.hansan.common.models.Formality

sealed interface LearnUiEvent {
    data class ChangeFormality(val value: Formality) : LearnUiEvent
    data class OnNavigate(val route: String) : LearnUiEvent
}