package com.jaegerapps.hansan.screens.learn.presentation.tense_list

import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.screens.learn.presentation.components.TenseHeader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TensesComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
    private val onNavigate: (String) -> Unit,
    private val onNavigateToTense: (String) -> Unit,
) : ComponentContext by componentContext {
    private val _state = MutableStateFlow(
        LearnUiState(
            filterFormality = Formality.FORMAL_HIGH,
            tenses = tenses,
            tensesShow = createTenseHashMap(tenses.filter { it.formality == Formality.FORMAL_HIGH })
        )
    )
    val state = _state.asStateFlow()

    fun onEvent(event: LearnUiEvent) {
        when (event) {
            is LearnUiEvent.ChangeFormality -> {
                _state.update { learnUiState ->
                    learnUiState.copy(
                        filterFormality = event.value,
                        tensesShow = createTenseHashMap(tenses.filter { it.formality == event.value })
                    )
                }
            }

            is LearnUiEvent.OnNavigate -> {
                onNavigate(event.route)
            }

            is LearnUiEvent.OnNavigateToTense -> {
                onNavigateToTense(event.tense)
            }
        }
    }

    private fun createTenseHashMap(tenses: List<TenseModel>): HashMap<TenseHeader, List<TenseModel>> {
        val hashMap: HashMap<TenseHeader, List<TenseModel>> = hashMapOf()
        hashMap[TenseHeader.PRESENT] = filterPresent(tenses)
        hashMap[TenseHeader.PAST] = filterPast(tenses)
        hashMap[TenseHeader.FUTURE] = filterFuture(tenses)
        return hashMap
    }

    private fun filterPresent(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.tense == Tense.PRESENT_DECLARATIVE ||
                    it.tense == Tense.PRESENT_DECLARATIVE_INQUISITIVE ||
                    it.tense == Tense.PRESENT_DECLARATIVE_NARRATIVE ||
                    it.tense == Tense.PRESENT_DECLARATIVE_SUGGESTIVE
        }
    }

    private fun filterPast(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.tense == Tense.PAST_DECLARATIVE
        }
    }

    private fun filterFuture(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.tense == Tense.FUTURE_DECLARATIVE ||
                    it.tense == Tense.FUTURE_DECLARATIVE_FIRST_PERSON
        }
    }
}