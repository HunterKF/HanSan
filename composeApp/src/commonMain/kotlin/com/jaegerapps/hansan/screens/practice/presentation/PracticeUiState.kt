package com.jaegerapps.hansan.screens.practice.presentation

import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.ModifierType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.WordModel
import com.jaegerapps.hansan.screens.practice.domain.models.AnswerResponse

data class PracticeUiState(
    val textInput: String = "",
    val currentWord: WordModel? = null,
    val targetTense: TenseModel? = null,
    val selectedFormalityCategory: Formality = Formality.FORMAL_HIGH,
    val enabledTenses: List<Tense> = emptyList(),
    val targetFormality: Formality = Formality.FORMAL_HIGH,
    val targetType: ModifierType = ModifierType.VERBS,
    val formalityList: List<Formality> = formalityDefaultList,
    val typeList: List<ModifierType> = modifierTypeList,
    val answerOptions: List<String> = emptyList(),
    val keyboardEnabled: Boolean = false,
    val openKeyboard: Boolean = false,
    val formalityDropDown: Boolean = false,
    val tenseExplanationExpanded: Boolean = false,
    val typeDropDown: Boolean = false,
    val answerResponse: AnswerResponse? = null,

    val dailyGoalMax: Int = 50,
    val dailyGoalMet: Int? = null,
    val errorMessage: PracticeErrorMessage? = null,
)

private val modifierTypeList = listOf(
    ModifierType.VERBS,
    ModifierType.ADVERBS,
    ModifierType.ADJECTIVES
)

private val formalityDefaultList = listOf(
    Formality.FORMAL_HIGH,
    Formality.FORMAL_LOW,
    Formality.INFORMAL_LOW,
    Formality.ALL
)
enum class PracticeErrorMessage {
    NOT_KOREAN,
    ANSWER_BLANK
}