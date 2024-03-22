package com.jaegerapps.hansan.presentation.practice.presentation

sealed interface PracticeUiEvent {

    data object ToggleTypeDropDown: PracticeUiEvent
    data object ToggleFormalityDropDown: PracticeUiEvent
    data class SelectType(val type: String): PracticeUiEvent
    data class SelectFormality(val formality: String): PracticeUiEvent

    data object ToggleKeyboard: PracticeUiEvent
    data class OnValueChange(val value: String): PracticeUiEvent
    data class ClickAnswer(val string: String): PracticeUiEvent
    data object EnterAnswerKeyboard: PracticeUiEvent

    data object ToggleTenseExplanation: PracticeUiEvent

    data class OnNavigate(val route: String): PracticeUiEvent

    data object ClearAnswer: PracticeUiEvent
}