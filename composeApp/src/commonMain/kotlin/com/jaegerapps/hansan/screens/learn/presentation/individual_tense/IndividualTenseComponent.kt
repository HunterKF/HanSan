package com.jaegerapps.hansan.screens.learn.presentation.individual_tense

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordUiEvent
import com.jaegerapps.hansan.screens.words.word_individual.IndividualWordUiState

class IndividualTenseComponent(
    currentTense: TenseModel,
    private val onNavigate: () -> Unit,
    componentContext: ComponentContext
): ComponentContext by componentContext {
    private val _state = mutableStateOf(
        currentTense
    )
    val state = _state.value

    fun onEvent(event: IndividualTenseUiEvent) {
        when (event) {
            IndividualTenseUiEvent.OnNavigateBack -> {
                onNavigate()
            }
        }
    }
}