package com.jaegerapps.hansan.screens.words.word_individual

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.Category
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.words.word_individual.domain.use_case.FilterTenseUseCase

class IndividualWordComponent(
    currentWord: VerbModel,
    private val onNavigate: () -> Unit,
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    init {
        Knower.d("IndividualWordComponent", "Here is the current word: $currentWord")
    }
    private val _state = mutableStateOf(
        IndividualWordUiState(
            currentWord = currentWord,
            present = FilterTenseUseCase.filterTense(currentWord, Category.PRESENT),
            past = FilterTenseUseCase.filterTense(currentWord, Category.PAST),
            future = FilterTenseUseCase.filterTense(currentWord, Category.FUTURE),
            other = FilterTenseUseCase.filterTense(currentWord, Category.OTHER)
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