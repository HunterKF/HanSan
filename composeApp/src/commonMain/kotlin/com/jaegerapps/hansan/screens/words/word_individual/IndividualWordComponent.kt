package com.jaegerapps.hansan.screens.words.word_individual

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word

class IndividualWordComponent(
    currentWord: VerbModel,
    private val onNavigate: () -> Unit,
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val _state = mutableStateOf(
        IndividualWordUiState(
            currentWord = currentWord,
            present = filterTense(currentWord, tense = Tense.PRESENT_DECLARATIVE),
            past = filterTense(currentWord, tense = Tense.PAST_DECLARATIVE),
            future = filterTense(currentWord, tense = Tense.FUTURE_DECLARATIVE)
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

    private fun filterTense(currentWord: VerbModel, tense: Tense): List<Word> {
        var pairList = emptyList<Word>()
        pairList = pairList.plus(currentWord.formalities.formalHigh.conjugation.filter { it.tense == tense })
        pairList = pairList.plus(currentWord.formalities.formalLow.conjugation.filter { it.tense == tense })
        pairList = pairList.plus(currentWord.formalities.informalLow.conjugation.filter { it.tense == tense })
        return pairList
    }
}