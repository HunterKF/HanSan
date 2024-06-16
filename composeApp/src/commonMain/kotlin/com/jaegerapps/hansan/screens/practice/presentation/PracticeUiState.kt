package com.jaegerapps.hansan.screens.practice.presentation

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel

data class PracticeUiState(
    //cursors are going to be used to get data from the Room database.
    //At first, we try with Time. If that returns an empty list, we switch to id.
    val timeCursor: Long? = null,
    val idCursor: Int = 0,
    //Target word is the current word on the screen
    val targetWord: PracticeWordModel? = null,
    //Target tense will be displayed above the word, showing what the user should be practicing.
    val targetTense: Tense? = null,
    //Displays which formality the target word is in
    val targetFormalityType: FormalityType? = null,
    //Shows the answer to the user
    val showAnswer: Boolean = false,
    //On launch, we check what tenses we have, this isn't used yet tbh
    /*TODO - Adjust Usecases to take in a tense.*/
    val enabledTenses: List<Tense> = emptyList(),
    val enabledFormalities: List<FormalityType> = emptyList(),
    val wordList: List<PracticeWordModel> = emptyList(),
    //Use to track the user's goals.
    val dailyGoalMax: Int = 50,
    val dailyGoalMet: Int? = null,
    //tbh we probably won't get errors unless room throws an error. I should add error handling to it.
    val errorMessage: PracticeErrorMessage? = null,
    val isLoading: Boolean = false
)
enum class PracticeErrorMessage {
    NOT_KOREAN,
    ANSWER_BLANK
}