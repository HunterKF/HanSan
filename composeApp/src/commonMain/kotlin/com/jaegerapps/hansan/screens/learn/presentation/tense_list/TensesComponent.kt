package com.jaegerapps.hansan.screens.learn.presentation.tense_list

import com.arkivanov.decompose.ComponentContext
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.DetailedTense
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
            filterFormalityType = FormalityType.FORMAL_HIGH,
            tenses = tenses,
            tensesShow = createTenseHashMap(tenses.filter { it.formalityType == FormalityType.FORMAL_HIGH })
        )
    )
    val state = _state.asStateFlow()

    fun onEvent(event: LearnUiEvent) {
        when (event) {
            is LearnUiEvent.ChangeFormality -> {
                _state.update { learnUiState ->
                    learnUiState.copy(
                        filterFormalityType = event.value,
                        tensesShow = createTenseHashMap(tenses.filter { it.formalityType == event.value })
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
        hashMap[TenseHeader.OTHER] = filterOther(tenses)
        return hashMap
    }

    private fun filterPresent(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.detailedTense == DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH ||
                    it.detailedTense == DetailedTense.PRESENT_DECLARATIVE_INFORMAL ||
                    it.detailedTense == DetailedTense.PRESENT_DECLARATIVE_FORMAL_LOW ||
                    it.detailedTense == DetailedTense.PRESENT_PROGRESSIVE_FORMAL_LOW ||
                    it.detailedTense == DetailedTense.PRESENT_PROGRESSIVE_FORMAL_HIGH ||
                    it.detailedTense == DetailedTense.PRESENT_PROGRESSIVE_INFORMAL_LOW
        }
    }

    private fun filterPast(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.detailedTense == DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.PAST_DECLARATIVE_FORMAL_LOW ||
            it.detailedTense == DetailedTense.PAST_DECLARATIVE_INFORMAL ||
            it.detailedTense == DetailedTense.PAST_PROGRESSIVE_FORMAL_LOW ||
            it.detailedTense == DetailedTense.PAST_PROGRESSIVE_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.PAST_PROGRESSIVE_INFORMAL
        }
    }

    private fun filterFuture(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.detailedTense == DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH ||
                    it.detailedTense == DetailedTense.FUTURE_DECLARATIVE_FORMAL_LOW||
                    it.detailedTense == DetailedTense.FUTURE_DECLARATIVE_INFORMAL||
                    it.detailedTense == DetailedTense.FUTURE_PROGRESSIVE_FORMAL_HIGH||
                    it.detailedTense == DetailedTense.FUTURE_PROGRESSIVE_FORMAL_LOW||
                    it.detailedTense == DetailedTense.FUTURE_PROGRESSIVE_INFORMAL
        }
    }
    private fun filterOther(tenses: List<TenseModel>): List<TenseModel> {
        return tenses.filter {
            it.detailedTense == DetailedTense.OTHER_IMPERATIVE_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.OTHER_IMPERATIVE_FORMAL_LOW
            it.detailedTense == DetailedTense.OTHER_IMPERATIVE_INFORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_PERMISSION_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.OTHER_PERMISSION_FORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_PERMISSION_INFORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_PROHIBITION_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.OTHER_PROHIBITION_FORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_PROHIBITION_INFORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_DESIRE_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.OTHER_DESIRE_FORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_DESIRE_INFORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_NECESSITY_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.OTHER_NECESSITY_FORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_NECESSITY_INFORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_POTENTIAL_FORMAL_HIGH ||
            it.detailedTense == DetailedTense.OTHER_POTENTIAL_FORMAL_LOW ||
            it.detailedTense == DetailedTense.OTHER_POTENTIAL_INFORMAL_LOW
        }
    }
}