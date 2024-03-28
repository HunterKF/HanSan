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

class PracticeComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
    private val words: List<WordModel>,
    private val onNavigate: (String) -> Unit,
    private val repo: PracticeRepo,
) : ComponentContext by componentContext {
    private val _state = MutableStateFlow(PracticeUiState())
    var userSettings = mutableStateOf<UserSettings?>(null)
    val state = _state.asStateFlow()


    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                override fun onCreate() {
                    Knower.d("onCreate", "onCreate is being called.")
                    initializePracticeComponent()
                }

                override fun onResume() {
                    Knower.d("onResume", "onResume is being called.")
                    initializePracticeComponent()
                }
            }
        )
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
        //Returns a list of 4 answers to be used for the clickable section
        val answerOptions = WordAndTenseHandler.newAnswerOptions(
            word,
            tense.tense,
            formality = _state.value.selectedFormalityCategory
        )

        _state.update {
            it.copy(
                selectedFormalityCategory = userSettings.value?.targetFormality
                    ?: Formality.FORMAL_HIGH,
                targetFormality = targetFormality,
                enabledTenses = enabledTenses,
                currentWord = word,
                targetTense = tense,
                answerOptions = answerOptions

            )
        }
        Knower.d("PracticeComponent - init", "Here are the values: $words \n $tenses")
    }

    fun onEvent(event: PracticeUiEvent) {
        when (event) {
            PracticeUiEvent.ClearAnswer -> {
                _state.update {
                    it.copy(answerResponse = null)
                }
            }

            is PracticeUiEvent.ClickAnswer -> {
                val result = _state.value.currentWord?.let {
                    EnterAnswer.textAnswer(
                        input = event.answer,
                        targetTense = _state.value.targetTense!!.tense,
                        wordModel = it,
                        formality = _state.value.targetFormality
                    )
                }
                result?.let { answer ->
                    when (answer) {
                        AnswerResponse.CORRECT -> {
                            val targetFormality =
                                returnTargetFormality(_state.value.selectedFormalityCategory)
                            val word = WordAndTenseHandler.newWord(words)
                            val tense = WordAndTenseHandler.newTense(
                                filterTenses(
                                    targetFormality,
                                    _state.value.enabledTenses
                                )
                            )
                            val answerOptions = WordAndTenseHandler.newAnswerOptions(
                                word,
                                tense.tense,
                                formality = targetFormality
                            )
                            _state.update {
                                it.copy(
                                    answerResponse = answer,
                                    textInput = "",
                                    currentWord = word,
                                    targetTense = tense,
                                    answerOptions = answerOptions,
                                    targetFormality = targetFormality
                                )
                            }
                        }

                        AnswerResponse.WRONG -> {
                            _state.update {
                                it.copy(
                                    answerResponse = answer,
                                    textInput = "",
                                )
                            }
                        }
                    }

                }
            }

            PracticeUiEvent.EnterAnswerKeyboard -> {
                val isKorean = _state.value.textInput.split(" ").map { it.isHangul() }

                if (_state.value.textInput.isEmpty()) {
                    _state.update {
                        it.copy(
                            errorMessage = PracticeErrorMessage.ANSWER_BLANK
                        )
                    }
                    return
                } else if (isKorean.contains(false)) {
                    _state.update {
                        it.copy(
                            textInput = "",
                            errorMessage = PracticeErrorMessage.NOT_KOREAN
                        )
                    }
                    return
                }
                val result = _state.value.currentWord?.let {
                    EnterAnswer.textAnswer(
                        input = _state.value.textInput,
                        targetTense = _state.value.targetTense!!.tense,
                        wordModel = it,
                        formality = _state.value.targetFormality
                    )
                }
                result?.let { answer ->
                    when (answer) {
                        AnswerResponse.CORRECT -> {
                            val targetFormality =
                                returnTargetFormality(_state.value.selectedFormalityCategory)
                            val word = WordAndTenseHandler.newWord(words)
                            val tense = WordAndTenseHandler.newTense(
                                filterTenses(
                                    targetFormality,
                                    _state.value.enabledTenses
                                )
                            )
                            val answerOptions = WordAndTenseHandler.newAnswerOptions(
                                word,
                                tense.tense,
                                formality = targetFormality
                            )
                            _state.update {
                                it.copy(
                                    answerResponse = answer,
                                    textInput = "",
                                    currentWord = word,
                                    targetTense = tense,
                                    answerOptions = answerOptions,
                                    targetFormality = targetFormality
                                )
                            }
                        }

                        AnswerResponse.WRONG -> {
                            Knower.e(
                                "EnterAnswerKeyboard",
                                "The answer was wrong. Here are the values: \n Tense: ${_state.value.targetTense} \n Word: ${_state.value.currentWord} \n Text input: ${_state.value.textInput}"
                            )
                            _state.update {
                                it.copy(
                                    answerResponse = answer,
                                    textInput = "",
                                )
                            }
                        }
                    }

                }
            }

            is PracticeUiEvent.OnNavigate -> {
                onNavigate(event.route)
            }

            is PracticeUiEvent.OnValueChange -> {
                _state.update {
                    it.copy(
                        textInput = event.value
                    )
                }
            }

            is PracticeUiEvent.SelectFormality -> {
                val formality =
                    getFormalityFromString(event.formality.lowercase().replace(" ", "_"))
                val targetFormality = returnTargetFormality(formality)
                val word = WordAndTenseHandler.newWord(words)
                val tense = WordAndTenseHandler.newTense(
                    filterTenses(
                        targetFormality,
                        _state.value.enabledTenses
                    )
                )
                val answerOptions = WordAndTenseHandler.newAnswerOptions(
                    word,
                    tense.tense,
                    formality = targetFormality
                )
                _state.update {
                    it.copy(
                        selectedFormalityCategory = formality,
                        targetFormality = targetFormality,
                        targetTense = tense,
                        formalityDropDown = false,
                        textInput = "",
                        currentWord = word,
                        answerOptions = answerOptions
                    )
                }
                /*_state.update {
                    it.copy(
                        targetFormality = formality,
                        targetTense = WordAndTenseHandler.newTense(filterTenses(formality)),
                        formalityDropDown = false
                    )
                }*/
            }

            is PracticeUiEvent.SelectType -> {
                val type = stringToType(event.type.lowercase())
                _state.update {
                    it.copy(
                        targetType = type,
                        typeDropDown = false
                    )
                }
            }

            PracticeUiEvent.ToggleFormalityDropDown -> {
                _state.update {
                    it.copy(
                        formalityDropDown = !_state.value.formalityDropDown,

                        typeDropDown = false
                    )
                }
            }

            PracticeUiEvent.ToggleKeyboardMode -> {
                Knower.d("ToggleKeyboard", "Hah, no keyboard yet.")
                _state.update {
                    it.copy(
                        keyboardEnabled = !_state.value.keyboardEnabled
                    )
                }
            }

            PracticeUiEvent.ToggleTypeDropDown -> {

                _state.update {
                    it.copy(
                        typeDropDown = !_state.value.typeDropDown,
                        formalityDropDown = false
                    )
                }
            }

            PracticeUiEvent.ToggleTenseExplanation -> {
                _state.update { it.copy(tenseExplanationExpanded = !_state.value.tenseExplanationExpanded) }
            }

            PracticeUiEvent.OpenKeyboard -> {
                _state.update {
                    it.copy(
                        openKeyboard = !_state.value.openKeyboard
                    )
                }
                Knower.d("openkeyboard", "Current state: ${_state.value}")
            }

            PracticeUiEvent.ClearErrorMessage -> {
                _state.update {
                    it.copy(errorMessage = null)
                }
            }
        }
    }

    private fun filterTenses(formality: Formality, tenseList: List<Tense>): List<TenseModel> {
        if (formality == Formality.ALL) return tenses.filter { tenseList.contains(it.tense) }
        return tenses.filter { tense -> tense.formality == formality && tenseList.contains(tense.tense) }
    }

    private fun returnTargetFormality(formality: Formality): Formality {
        val formalityList =
            listOf(Formality.FORMAL_HIGH, Formality.FORMAL_LOW, Formality.INFORMAL_LOW)
        return if (formality == Formality.ALL) {
            formalityList.random()
        } else {
            _state.value.selectedFormalityCategory
        }
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


}