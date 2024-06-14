package com.jaegerapps.hansan.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//This is coming from the JSON data.
//This will be used in the following places
//Onboarding: Takes the JSON data and stores it into the Room database as words for the user to practice
//Learn: This will be used to display the data in learn for the user to click on a word and then check out its grammar properties
@Serializable
data class VerbEntity(
    val id: Int,
    @SerialName("base")
    val base: String,
    @SerialName("translations")
    val translationsEntity: TranslationsEntity,
    @SerialName("formalities")
    val formalitiesEntity: FormalitiesEntity
)

@Serializable
data class TranslationsEntity(
    @SerialName("english")
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
    val conjugations: ConjugationDto
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

