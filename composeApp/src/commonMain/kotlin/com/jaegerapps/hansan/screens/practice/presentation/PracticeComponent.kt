package com.jaegerapps.hansan.screens.practice.presentation

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.Knower.e
import com.jaegerapps.hansan.screens.practice.domain.hangul.isHangul
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.practice.domain.usecases.EnterAnswer
import com.jaegerapps.hansan.screens.practice.domain.usecases.WordAndTenseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PracticeComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
    private val words: List<WordModel>,
    private val onNavigate: (String) -> Unit,
    private val repo: PracticeRepo,
) : ComponentContext by componentContext {

    private var userSettings = mutableStateOf<UserSettings?>(null)

    private val _state = MutableStateFlow(PracticeUiState())
    val state = _state.asStateFlow()


    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        Knower.d("onCreate", "onCreate is being called.")
        initializePracticeComponent()
        Knower.d("init", "Initializing PracticeComponent")
        lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                override fun onCreate() {

                }

                override fun onResume() {
                    Knower.d("onResume", "onResume is being called.")
                    initializePracticeComponent()
                }
            }
        )
    }


    fun onEvent(event: PracticeUiEvent) {
        when (event) {

            is PracticeUiEvent.OnNavigate -> {
                onNavigate(event.route)
            }


            PracticeUiEvent.ClearErrorMessage -> {
                _state.update {
                    it.copy(errorMessage = null)
                }
            }

            PracticeUiEvent.CheckAnswer -> {
                _state.update {
                    it.copy(
                        showAnswer = true
                    )
                }
                Knower.d("CheckAnswer", "This has updated: ${_state.value.showAnswer}")
                Knower.d("CheckAnswer", "This has updated: ${state.value.showAnswer}")
            }
            PracticeUiEvent.ClickDon_tKnow -> {
                /*TODO - Perform some logic here*/
                _state.update {
                    it.copy(
                        showAnswer = false
                    )
                }
            }
            PracticeUiEvent.ClickGotIt -> {
                /*TODO - Perform some logic here*/
                _state.update {
                    it.copy(
                        showAnswer = false
                    )
                }
            }
        }
    }

    private fun filterTenses(formality: Formality, tenseList: List<Tense>): List<TenseModel> {
        return tenses.filter { tense -> tense.formality == formality && tenseList.contains(tense.tense) }
    }

    private fun returnTargetFormality(formality: Formality): Formality {
        return _state.value.selectedFormalityCategory

    }

    private fun initializePracticeComponent() {
        scope.launch {
            userSettings.value = async { repo.getUserSettings() }.await()
        }
        //The target is going to be the current one displayed
        val targetFormality = returnTargetFormality(
            userSettings.value?.targetFormality ?: Formality.FORMAL_HIGH
        )
        //This takes the enabled tenses from the user's settings and creates a list of Tenses to be used to filter
        val enabledTenses = filterTensesByUserSettings(
            presentTense = userSettings.value?.presentTenseEnabled ?: true,
            pastTense = userSettings.value?.pastTenseEnabled ?: true,
            futureTense = userSettings.value?.futureTenseEnabled ?: true,
        )
        //Gets a random word
        val word = WordAndTenseHandler.newWord(words)
        //Gets a random tense. It will filter based on formality and enabled tenses
        val tense = WordAndTenseHandler.newTense(
            filterTenses(
                targetFormality,
                enabledTenses
            )
        )

        _state.update {
            it.copy(
                selectedFormalityCategory = userSettings.value?.targetFormality
                    ?: Formality.FORMAL_HIGH,
                targetFormality = targetFormality,
                enabledTenses = enabledTenses,
                currentWord = word,
                targetTense = tense,
                dailyGoalMax = userSettings.value?.dailyTargetMax ?: 50,
                dailyGoalMet = userSettings.value?.dailyTargetMet ?: 0

            )
        }
        Knower.d("PracticeComponent - init", "Here are the values: $words \n $tenses")
    }


    private fun filterTensesByUserSettings(
        presentTense: Boolean,
        pastTense: Boolean,
        futureTense: Boolean,
    ): List<Tense> {
        var list = emptyList<Tense>()
        if (presentTense) list = list.plus(Tense.PRESENT_DECLARATIVE)
        if (pastTense) list = list.plus(Tense.PAST_DECLARATIVE)
        if (futureTense) list = list.plus(Tense.FUTURE_DECLARATIVE)
        return list
    }

    private fun updateDailyTargetMet(newValue: Int): Int {

        if (newValue + 1 == _state.value.dailyGoalMax) return newValue
        scope.launch {
            repo.updateDailyGoalMet(newValue)
        }
        return newValue + 1

    }


}