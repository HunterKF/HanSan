package com.jaegerapps.hansan.screens.learn.presentation

import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.TenseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LearnComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
    private val onNavigate: (String) -> Unit,
) : ComponentContext by componentContext {
    private val _state = MutableStateFlow(LearnUiState(
        filterFormality = Formality.FORMAL_HIGH,
        tenses = tenses,
        tensesShow = tenses.filter { it.formality ==  Formality.FORMAL_HIGH}
    ))
    val state = _state.asStateFlow()

    fun onEvent(event: LearnUiEvent) {
        when (event) {
            is LearnUiEvent.ChangeFormality -> {
                _state.update {
                    it.copy(
                        filterFormality = event.value,
                        tensesShow = tenses.filter { it.formality == event.value }
                    )
                }
            }
            is LearnUiEvent.OnNavigate -> {
                onNavigate(event.route)
            }
        }
    }
}