package com.jaegerapps.hansan.screens.settings.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsComponent(
    componentContext: ComponentContext,
    private val repo: SettingsRepo,
    private val onNavigate: (String) -> Unit,
) : ComponentContext by componentContext {

    private val _state = MutableStateFlow(SettingsUiState())
    val state = _state.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                override fun onCreate() {
                    scope.launch {
                        val result = async { repo.getUserSettings() }.await()
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(
                                    presentTenseEnabled = result.presentTenseEnabled,
                                    pastTenseEnabled = result.pastTenseEnabled,
                                    futureTenseEnabled = result.futureTenseEnabled,
                                    enableReminders = result.enableReminders,
                                    dailyTarget = result.dailyTarget,
                                    loading = false
                                )
                            }
                        }
                    }
                }
            }
        )
    }

    fun onEvent(event: SettingsUiEvent) {
        when (event) {
            is SettingsUiEvent.ChangeDailyTarget -> {
                if (event.number.isBlank()) {
                    _state.update {
                        it.copy(
                            errorMessage = SettingsErrorMessage.DAILY_BLANK
                        )
                    }
                    return
                }
                scope.launch {
                    val result = async { repo.updateDailyTarget(returnNumbers(event.number)) }.await()
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                dailyTarget = result
                            )
                        }
                    }
                }
            }

            is SettingsUiEvent.OnNavigate -> {
                onNavigate(event.route)
            }

            is SettingsUiEvent.ToggleDailyReminders -> {

                scope.launch {
                    val result = async { repo.enableDailyReminders(event.value) }.await()
                    _state.update {
                        it.copy(
                            enableReminders = result
                        )
                    }
                }
            }

            is SettingsUiEvent.ToggleFutureTense -> {
                if (
                    !atLeastOneTenseEnabled(
                        _state.value.presentTenseEnabled,
                        _state.value.pastTenseEnabled,
                        event.value
                    )
                ) {
                    _state.update { it.copy(errorMessage = SettingsErrorMessage.TENSE_BLANK) }
                    return
                }
                scope.launch {
                    val result = async { repo.updateFutureTense(event.value) }.await()
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                futureTenseEnabled = result
                            )
                        }
                    }
                }
            }

            is SettingsUiEvent.TogglePastTense -> {
                if (
                    !atLeastOneTenseEnabled(
                        _state.value.presentTenseEnabled,
                        event.value,
                        _state.value.futureTenseEnabled
                    )
                ) {
                    _state.update { it.copy(errorMessage = SettingsErrorMessage.TENSE_BLANK) }
                    return
                }
                scope.launch {
                    val result = async { repo.updatePastTense(event.value) }.await()
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                pastTenseEnabled = result
                            )
                        }
                    }
                }
            }

            is SettingsUiEvent.TogglePresentTense -> {
                if (
                    !atLeastOneTenseEnabled(
                        _state.value.presentTenseEnabled,
                        _state.value.pastTenseEnabled,
                        event.value
                    )
                ) {
                    _state.update { it.copy(errorMessage = SettingsErrorMessage.TENSE_BLANK) }
                    return
                }
                scope.launch {
                    val result = async { repo.updatePresentTense(event.value) }.await()
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                presentTenseEnabled = result
                            )
                        }
                    }
                }
            }

            SettingsUiEvent.ClearErrorMessage -> _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun returnNumbers(value: String): Int {
        return value.take(3).filter { it.isDigit() }.toInt()
    }
    private fun atLeastOneTenseEnabled(
        presentTense: Boolean,
        pastTense: Boolean,
        futureTense: Boolean
    ): Boolean {
        //true, false, false
        //false, false, false
        return presentTense || pastTense || futureTense
    }
}