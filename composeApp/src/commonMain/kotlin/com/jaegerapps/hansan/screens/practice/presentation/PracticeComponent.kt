package com.jaegerapps.hansan.screens.practice.presentation

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel
import com.jaegerapps.hansan.screens.practice.domain.repo.PracticeRepo
import com.jaegerapps.hansan.screens.practice.domain.usecases.LevelUseCase
import com.jaegerapps.hansan.screens.practice.domain.usecases.TimeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class PracticeComponent(
    componentContext: ComponentContext,
    private val tenses: List<TenseModel>,
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
            }

            PracticeUiEvent.ClickDon_tKnow -> {
                onDontKnow()
            }

            PracticeUiEvent.ClickGotIt -> {
                onGotIt()
            }
        }
    }

    private fun initializePracticeComponent() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        scope.launch {
            userSettings.value = async { repo.getUserSettings() }.await()
            async {getNewWords()  }.invokeOnCompletion {
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
    private fun updateDailyTargetMet(newValue: Int): Int {

        if (newValue + 1 == _state.value.dailyGoalMax) return newValue
        scope.launch {
            repo.updateDailyGoalMet(newValue)
        }
        return newValue + 1

    }

    private fun onGotIt() {
        scope.launch {
            repo.updateWord(updateWordLevelUp(word = _state.value.targetWord!!))
            removeAndSetNewTargetWord()
            _state.update {
                it.copy(
                    showAnswer = false,
                    dailyGoalMet = updateDailyTargetMet(it.dailyGoalMet ?: 0)
                )
            }
            if (_state.value.wordList.size < 5) {
                getNewWords()
            }
        }
    }

    private fun onDontKnow() {
        scope.launch {
            repo.updateWord(updateWordLevelDown(word = _state.value.targetWord!!))
            removeAndSetNewTargetWord()
            _state.update {
                it.copy(
                    showAnswer = false,
                    dailyGoalMet = updateDailyTargetMet(it.dailyGoalMet ?: 0)
                )
            }
            if (_state.value.wordList.size < 5) {
                getNewWords()
            }
        }
    }

    private fun updateWordLevelUp(word: PracticeWordModel): PracticeWordModel {
        val level = LevelUseCase.updateLevelUp(word.level)
        return word.copy(
            level = level,
            dateExpire = TimeUseCase.setTime(level, getTime())
        )
    }

    private fun getTime(): LocalDateTime {
        val now = Clock.System.now()
        val currentDateTime: LocalDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        return currentDateTime
    }

    private fun updateWordLevelDown(word: PracticeWordModel): PracticeWordModel {
        val level = LevelUseCase.updateLevelDown(word.level)
        return word.copy(
            level = level,
            dateExpire = TimeUseCase.setTime(level, getTime())
        )
    }

    private fun removeAndSetNewTargetWord() {
        _state.update {
            it.copy(
                wordList = it.wordList.drop(1),
                targetWord = it.wordList[1],
                targetTense = it.wordList[1].tense,
                targetFormalityType = it.wordList[1].formality
            )
        }
    }

    private suspend fun getNewWords() {
        scope.launch {
            var words = async {
                getWordsByTime()
            }.await()
            if (words.isEmpty()) {
                Knower.d("PracticeComponent", "Words by time was empty. Attempting to get by id.")

                words = async {
                    getWordsById()
                }.await()
            }
            if (words.isEmpty()) {
                Knower.d("PracticeComponent", "Words by id was empty. Attempting to get by no cursor.")
                words = async {
                    getWordsNoCursor()
                }.await()
            }
            if (words.isEmpty()) {
                Knower.d("PracticeComponent", "No luck, still empty.")

            }
            _state.update {
                it.copy(
                    wordList = it.wordList + words,
                    targetWord = words.firstOrNull(),
                    targetTense = words.firstOrNull()?.tense,
                    targetFormalityType = words.firstOrNull()?.formality
                )
            }
            Knower.d("PracticeComponent", "Got words. Here is the updated state \n ${_state.value}")
        }
    }

    private suspend fun getWordsByTime(): List<PracticeWordModel> {
        return repo.getWordsByTime(
            _state.value.timeCursor ?: Clock.System.now().toEpochMilliseconds()
        )
    }

    private suspend fun getWordsById(): List<PracticeWordModel> {
        return repo.getWordsById(_state.value.idCursor)
    }

    private suspend fun getWordsNoCursor(): List<PracticeWordModel> {
        return repo.getWords()
    }


}