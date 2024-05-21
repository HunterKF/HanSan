package com.jaegerapps.hansan.screens.practice.presentation

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word

data class PracticeUiState(
    val textInput: String = "",
    val currentVerb: VerbModel? = null,
    val targetWord: Word? = null,
    val targetTense: TenseModel? = null,
    val selectedFormalityCategoryType: FormalityType = FormalityType.FORMAL_HIGH,
    val enabledTenses: List<Tense> = emptyList(),
    val targetFormalityType: FormalityType = FormalityType.FORMAL_HIGH,
    val targetType: ModifierType = ModifierType.VERBS,
    val formalityTypeList: List<FormalityType> = formalityTypeDefaultLists,
    val typeList: List<ModifierType> = modifierTypeList,

    val dailyGoalMax: Int = 50,
    val dailyGoalMet: Int? = null,
    val errorMessage: PracticeErrorMessage? = null,
    val showAnswer: Boolean = false
)

private val modifierTypeList = listOf(
    ModifierType.VERBS,
    ModifierType.ADJECTIVES
)

private val formalityTypeDefaultLists = listOf(
    FormalityType.FORMAL_HIGH,
    FormalityType.FORMAL_LOW,
    FormalityType.INFORMAL_LOW,
)
enum class PracticeErrorMessage {
    NOT_KOREAN,
    ANSWER_BLANK
}