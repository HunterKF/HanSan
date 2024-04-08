package com.jaegerapps.hansan.common.models

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.tense_future
import hansan.composeapp.generated.resources.tense_past
import hansan.composeapp.generated.resources.tense_present
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
    PAST_DECLARATIVE,
    FUTURE_DECLARATIVE,
}

fun getTenseFromString(value: String): Tense {
    return when (value) {
        "present_declarative" -> Tense.PRESENT_DECLARATIVE
        "past_declarative" -> Tense.PAST_DECLARATIVE
        "future_declarative" -> Tense.FUTURE_DECLARATIVE
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
    }
}
