package com.jaegerapps.hansan.common.models

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.tense_future
import hansan.composeapp.generated.resources.tense_future_first_person
import hansan.composeapp.generated.resources.tense_past
import hansan.composeapp.generated.resources.tense_present
import hansan.composeapp.generated.resources.tense_present_inquisitive
import hansan.composeapp.generated.resources.tense_present_narrative
import hansan.composeapp.generated.resources.tense_present_suggestive
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

data class TenseModel(
    val tense: Tense,
    val formality: Formality,
    val conjugation: String,
    val explanation: String,
    val exampleGada: String,
    val exampleBoda: String,
    val exampleMokda: String,
    val exampleHada: String,
    //ㅅ
    val irregularSieut: String?,
    //ㄷ
    val irregularDieut: String?,
    //ㅂ
    val irregularBieub: String?,
    //ㅡ
    val irregularEu: String?,
    //르
    val irregularReu: String?,
    //ㄹ
    val irregularRieul: String?,
)

enum class Tense {
    PRESENT_DECLARATIVE,
    PRESENT_DECLARATIVE_INQUISITIVE,
    PRESENT_DECLARATIVE_NARRATIVE,
    PRESENT_DECLARATIVE_SUGGESTIVE,
    PAST_DECLARATIVE,
    FUTURE_DECLARATIVE,
    FUTURE_DECLARATIVE_FIRST_PERSON,
}

fun getTenseFromString(value: String): Tense {
    return when (value) {
        "present_declarative" -> Tense.PRESENT_DECLARATIVE
        "present_declarative_inquisitive" -> Tense.PRESENT_DECLARATIVE_INQUISITIVE
        "present_declarative_narrative" -> Tense.PRESENT_DECLARATIVE_NARRATIVE
        "present_declarative_suggestive" -> Tense.PRESENT_DECLARATIVE_SUGGESTIVE
        "past_declarative" -> Tense.PAST_DECLARATIVE
        "future_declarative" -> Tense.FUTURE_DECLARATIVE
        "future_declarative_first_person" -> Tense.FUTURE_DECLARATIVE_FIRST_PERSON
        else -> {
            Tense.PRESENT_DECLARATIVE
        }
    }
}
@OptIn(ExperimentalResourceApi::class)
fun getTenseResString(value: Tense): StringResource {
    return when (value) {
        Tense.PRESENT_DECLARATIVE -> Res.string.tense_present
        Tense.PAST_DECLARATIVE -> Res.string.tense_past
        Tense.FUTURE_DECLARATIVE -> Res.string.tense_future
        Tense.PRESENT_DECLARATIVE_INQUISITIVE -> Res.string.tense_present_inquisitive
        Tense.PRESENT_DECLARATIVE_NARRATIVE -> Res.string.tense_present_narrative
        Tense.PRESENT_DECLARATIVE_SUGGESTIVE -> Res.string.tense_present_suggestive
        Tense.FUTURE_DECLARATIVE_FIRST_PERSON -> Res.string.tense_future_first_person
    }
}
