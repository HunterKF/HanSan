package com.jaegerapps.hansan.common.models

data class VerbModel(
    val baseWord: String,
    val definitionTranslations: DefinitionTranslations,
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
    val tense: Tense,
    val conjugatedWord: String,
    val irregular: Boolean
)

data class DefinitionTranslations(
    val english: String
)

data class WordTenseModel(
    val string: String,
    val tense: Tense,
    val formalityType: FormalityType
)

