package com.jaegerapps.hansan.common.models

import kotlinx.serialization.Serializable

@Serializable
data class WordEntity(
    val id: Int,
    val dictionary_form: String,
    val dictionary_definition: String,
    val type: String,
    val irregular: Boolean,
    val formal_high_present_declarative: String,
    val formal_high_past_declarative: String,
    val formal_high_future_declarative: String,
    val formal_low_present_declarative: String,
    val formal_low_past_declarative: String,
    val formal_low_future_declarative: String,
    val informal_low_present_declarative: String,
    val informal_low_past_declarative: String,
    val informal_low_future_declarative: String,
)
@Serializable
data class VerbEntity(
    val id: Int,
    val base: String,
    val translationsEntity: TranslationsEntity,
    val formalitiesEntity: FormalitiesEntity
)

@Serializable
data class TranslationsEntity(
    val english: String
)

@Serializable
data class FormalitiesEntity(
    val formal_high: FormalityContainerDto,
    val formal_low: FormalityContainerDto,
    val informal_low: FormalityContainerDto
)

@Serializable
data class FormalityContainerDto(
    val conjugation: ConjugationDto
)


@Serializable
data class ConjugationDto(
    val present: PresentDto,
    val past: PastDto,
    val future: FutureDto
)


@Serializable
data class PresentDto(
    val declarative: WordDto
)

@Serializable
data class PastDto(
    val declarative: WordDto
)

@Serializable
data class FutureDto(
    val declarative: WordDto
)

@Serializable
data class WordDto(
    val conjugated: String,
    val irregular: Boolean
)

