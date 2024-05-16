package com.jaegerapps.hansan.screens.practice.presentation

sealed interface PracticeUiEvent {
    data class OnNavigate(val route: String): PracticeUiEvent

    data object ClickGotIt: PracticeUiEvent
    data object ClickDon_tKnow: PracticeUiEvent
    data object CheckAnswer: PracticeUiEvent
    data object ClearErrorMessage: PracticeUiEvent
}