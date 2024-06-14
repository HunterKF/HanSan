package com.jaegerapps.hansan.screens.practice.presentation

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word

data class PracticeUiState(
    val timeCursor: Long? = null,
    val idCursor: Int = 0,
    val targetWord: Word? = null,
    val targetTense: TenseModel? = null,
    val enabledTenses: List<Tense> = emptyList(),
    val targetFormalityType: List<FormalityType> = emptyList(),
    val dailyGoalMax: Int = 50,
    val dailyGoalMet: Int? = null,
    val errorMessage: PracticeErrorMessage? = null,
    val showAnswer: Boolean = false
)
enum class PracticeErrorMessage {
    NOT_KOREAN,
    ANSWER_BLANK
}