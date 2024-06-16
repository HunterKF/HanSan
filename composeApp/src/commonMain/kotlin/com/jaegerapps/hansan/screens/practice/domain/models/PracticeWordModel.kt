package com.jaegerapps.hansan.screens.practice.domain.models

import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.Tense

data class PracticeWordModel(
    val id: Int,
    val baseWord: String,
    val translations: List<PracticeTranslation>,
    val level: Level = Level.LEVEL_ONE,
    val dateExpire: Long?,
    val tense: Tense,
    val formality: FormalityType,
    val conjugatedWord: String,
    val irregular: Boolean
)
data class PracticeTranslation(
    val languageCode: String,
    val translation: String
)