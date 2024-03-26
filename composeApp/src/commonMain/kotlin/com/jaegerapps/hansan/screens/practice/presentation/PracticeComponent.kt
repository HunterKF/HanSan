package com.jaegerapps.hansan.screens.practice.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.stringToType
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.practice.domain.hangul.HangulToolkit
import com.jaegerapps.hansan.screens.practice.domain.hangul.isHangul
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse
import com.jaegerapps.hansan.screens.practice.domain.usecases.EnterAnswer
import com.jaegerapps.hansan.screens.practice.domain.usecases.WordAndTenseHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PracticeComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
    private val words: List<WordModel>,
    private val onNavigate: (String) -> Unit,
) : ComponentContext by componentContext {
    private val _state = MutableStateFlow(PracticeUiState())
    val state = _state.asStateFlow()

    val toolkit = HangulToolkit()

    init {
        lifecycle.doOnCreate {
            _state.update {
                it.copy(
                    currentWord = words.firstOrNull(),
                    targetTense = tenses.firstOrNull(),

                    )
            }
            Knower.d("PracticeComponent - init", "Here are the values: $words \n $tenses")

        }
    }

    fun onEvent(event: PracticeUiEvent) {
        when (event) {
            PracticeUiEvent.ClearAnswer -> {
                _state.update {
                    it.copy(answerResponse = null)
                }
            }

            is PracticeUiEvent.ClickAnswer -> TODO()
            PracticeUiEvent.EnterAnswerKeyboard -> {
                if (_state.value.textInput.isEmpty()) {
                    _state.update {
                        it.copy(
                            errorMessage = PracticeErrorMessage.ANSWER_BLANK
                        )
                    }
                    return
                }
                val result = _state.value.currentWord?.let {
                    EnterAnswer.textAnswer(
                        _state.value.textInput, _state.value.targetTense!!.tense,
                        it
                    )
                }
                result?.let { answer ->
                    when (answer) {
                        AnswerResponse.CORRECT -> {
                            _state.update {
                                it.copy(
                                    answerResponse = answer,
                                    textInput = "",
                                    currentWord = WordAndTenseHandler.newWord(words),
                                    targetTense = WordAndTenseHandler.newTense(tenses)
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

            is PracticeUiEvent.OnNavigate -> {
                onNavigate(event.route)
            }

            is PracticeUiEvent.OnValueChange -> {
                if (!event.value.isHangul()) {
                    _state.update {
                        it.copy(
                            errorMessage = PracticeErrorMessage.NOT_KOREAN
                        )
                    }
                } else if (event.value.isEmpty()){
                    _state.update {
                        it.copy(
                            errorMessage = PracticeErrorMessage.ANSWER_BLANK
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            textInput = event.value
                        )
                    }
                }
            }

            is PracticeUiEvent.SelectFormality -> {
                _state.update {
                    it.copy(
                        targetFormality = getFormalityFromString(event.formality)
                    )
                }
            }

            is PracticeUiEvent.SelectType -> {
                _state.update {
                    it.copy(
                        targetType = stringToType(event.type)
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
}