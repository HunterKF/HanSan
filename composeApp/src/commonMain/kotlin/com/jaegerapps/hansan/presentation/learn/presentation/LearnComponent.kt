package com.jaegerapps.hansan.presentation.learn.presentation

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.TenseModel

class LearnComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
    private val onNavigate: (String) -> Unit,
) : ComponentContext by componentContext {
    private val _state = mutableStateOf(LearnUiState(
        tenses = tenses
    ))
    val state = _state

    fun onEvent(value: String) {
            onNavigate(value)
    }
}