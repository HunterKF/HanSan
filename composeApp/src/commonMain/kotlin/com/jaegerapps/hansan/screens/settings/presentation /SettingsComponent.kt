package com.jaegerapps.hansan.screens.settings.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsFormalityModel
import com.jaegerapps.hansan.screens.settings.domain.models.SettingsTenseModel
import com.jaegerapps.hansan.screens.settings.domain.repo.SettingsRepo
import com.jaegerapps.hansan.screens.settings.domain.use_cases.CheckEnabledUseCase.Companion.checkFormalitiesAtLeastOneEnabled
import com.jaegerapps.hansan.screens.settings.domain.use_cases.CheckEnabledUseCase.Companion.checkTensesAtLeastOneEnabled
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        initializeComponent()

        lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                override fun onCreate() {
                    scope.launch {
                        withContext(Dispatchers.Main) {
                            /*_state.update {
                                it.copy(
                                    presentTenseEnabled = result.presentTenseEnabled,
                                    pastTenseEnabled = result.pastTenseEnabled,
                                    futureTenseEnabled = result.futureTenseEnabled,
                                    enableReminders = result.enableReminders,
                                    dailyTarget = result.dailyTargetMax,
                                    loading = false
                                )
                            }*/
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
                    val result =
                        async { repo.updateDailyTarget(returnNumbers(event.number)) }.await()
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


            SettingsUiEvent.ClearErrorMessage -> _state.update { it.copy(errorMessage = null) }
            is SettingsUiEvent.ToggleFormality -> {
                if (!checkFormalitiesAtLeastOneEnabled(_state.value.formalities, _state.value.formalities.size) && !event.value) {
                    _state.update { it.copy(errorMessage = SettingsErrorMessage.TENSE_BLANK) }
                    return
                } else {
                    updateFormality(event.formality, event.value)
                }
            }

            is SettingsUiEvent.ToggleTense -> {
                if (!checkTensesAtLeastOneEnabled(_state.value.tenses, _state.value.tenses.size)  && !event.value) {
                    _state.update { it.copy(errorMessage = SettingsErrorMessage.TENSE_BLANK) }
                    return
                } else {
                    updateTense(event.detailedTense, event.value)
                }
            }
        }
    }

    private fun returnNumbers(value: String): Int {
        return value.take(3).filter { it.isDigit() }.toInt()
    }


    private fun updateFormality(formality: FormalityType, isSelected: Boolean) {
        scope.launch {
            repo.toggleFormality(formality, tenses = _state.value.tenses.filter { it.isSelected }, isSelected)
            _state.update {
                it.copy(
                    formalities = updateSingleFormality(
                        _state.value.formalities,
                        target = formality,
                        isSelected = isSelected
                    )
                )
            }
        }
    }


    private fun updateSingleFormality(
        list: List<SettingsFormalityModel>,
        target: FormalityType,
        isSelected: Boolean,
    ): List<SettingsFormalityModel> {
        return list.map { if (it.formalityType == target) it.copy(isSelected = isSelected) else it }
    }



    private fun updateTense(detailedTense: DetailedTense, isSelected: Boolean) {
        scope.launch {
            repo.toggleTense(detailedTense = detailedTense, formalities = _state.value.formalities.filter { it.isSelected }, isSelected = isSelected)
            _state.update {
                it.copy(
                    tenses = updateSingleTense(
                        _state.value.tenses,
                        target = detailedTense,
                        isSelected = isSelected,
                    )
                )
            }
        }
    }


    private fun updateSingleTense(
        list: List<SettingsTenseModel>,
        target: DetailedTense,
        isSelected: Boolean,
    ): List<SettingsTenseModel> {
        return list.map { if (it.detailedTense == target) it.copy(isSelected = isSelected) else it }
    }


    private fun initializeComponent() {
        scope.launch {
            val enabled = async { repo.getGrammar() }.await()
            val userSettings = async { repo.getUserSettings()}.await()
            _state.update {
                it.copy(
                    dailyTarget = userSettings.dailyTargetMax,
                    enableReminders = userSettings.enableReminders,
                    formalities = enabled.first,
                    tenses = enabled.second
                )
            }
        }
    }
}