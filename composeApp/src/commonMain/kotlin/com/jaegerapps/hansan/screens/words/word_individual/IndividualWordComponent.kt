package com.jaegerapps.hansan.screens.words.word_individual

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.VerbModel

class IndividualWordComponent(
    currentWord: VerbModel,
    private val onNavigate: () -> Unit,
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    /*private val _state = mutableStateOf(
        IndividualWordUiState(
            currentWord = currentWord,
            present = listOf(
                currentWord.formalities.formalLow.conjugation.present.declarative.conjugatedWord,
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
    )*/
//    val state = _state.value

    fun onEvent(event: IndividualWordUiEvent) {
        when (event) {
            IndividualWordUiEvent.OnNavigateBack -> {
                onNavigate()
            }
        }
    }
}