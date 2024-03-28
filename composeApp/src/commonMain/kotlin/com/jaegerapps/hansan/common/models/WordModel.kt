package com.jaegerapps.hansan.common.models

data class WordModel(
    val dictionaryWord: String,
    val definition: String,
    val type: ModifierType,
    val irregular: Boolean,
    //fh - formal high
    val fhPresentDeclarative: WordTenseModel,
    val fhPastDeclarative: WordTenseModel,
    val fhFutureDeclarative: WordTenseModel,
    //fl - formal low
    val flPresentDeclarative: WordTenseModel,
    val flPastDeclarative: WordTenseModel,
    val flFutureDeclarative: WordTenseModel,
    //il - informal low
    val ilPresentDeclarative: WordTenseModel,
    val ilPastDeclarative: WordTenseModel,
    val ilFutureDeclarative: WordTenseModel,
)

data class WordTenseModel(
    val string: String,
    val tense: Tense,
    val formality: Formality
)

