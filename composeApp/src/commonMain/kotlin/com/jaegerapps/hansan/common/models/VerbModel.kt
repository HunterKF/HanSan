package com.jaegerapps.hansan.common.models

import com.jaegerapps.hansan.screens.practice.domain.models.Level

//Verb model will be used to display words on the word screen.
data class VerbModel(
    val baseWord: String,
    val definitionTranslations: List<DefinitionTranslation>,
//    val type: ModifierType,
    val formalities: Formalities,
)

data class Formalities(
    val formalHigh: Formality,
    val formalLow: Formality,
    val informalLow: Formality
)

data class Formality(
    val type: FormalityType,
    val conjugation: List<Word>
)

data class Word(
    val level: Level = Level.LEVEL_ONE,
    val dateExpire: Long?,
    val tense: Tense,
    val formality: FormalityType,
    val conjugatedWord: String,
    val irregular: Boolean
)

data class DefinitionTranslation(
    val languageCode: String,
    val translation: String
)

data class WordTenseModel(
    val string: String,
    val tense: Tense,
    val formalityType: FormalityType
)

