package com.jaegerapps.hansan.presentation.words.word_individual

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.WordModel

class IndividualWordComponent(
    currentWord: WordModel,
    private val onNavigate: () -> Unit,
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val _state = mutableStateOf(
        IndividualWordUiState(
            currentWord = currentWord,
            present = listOf(
                currentWord.fhPresentDeclarative,
                currentWord.flPresentDeclarative,
                currentWord.ilPresentDeclarative
            ),
            past = listOf(
                currentWord.fhPastDeclarative,
                currentWord.flPastDeclarative,
                currentWord.ilPastDeclarative
            ),
            future = listOf(
                currentWord.fhFutureDeclarative,
                currentWord.flFutureDeclarative,
                currentWord.ilFutureDeclarative
            )
        )
    )
    val state = _state.value

    fun onEvent(event: IndividualWordUiEvent) {
        when (event) {
            IndividualWordUiEvent.OnNavigateBack -> {
                onNavigate()
            }
        }
    }
}