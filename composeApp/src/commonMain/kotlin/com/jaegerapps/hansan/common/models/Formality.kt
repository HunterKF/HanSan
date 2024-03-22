package com.jaegerapps.hansan.common.models

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.formality_formal_high
import hansan.composeapp.generated.resources.formality_formal_low
import hansan.composeapp.generated.resources.formality_informal_low
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

enum class Formality {
    FORMAL_HIGH,
    FORMAL_LOW,
    INFORMAL_LOW
}
@OptIn(ExperimentalResourceApi::class)
fun formalityToStringResource(
    formality: Formality,
): StringResource {
    return when (formality) {
        Formality.FORMAL_HIGH -> Res.string.formality_formal_high
        Formality.FORMAL_LOW -> Res.string.formality_formal_low
        Formality.INFORMAL_LOW -> Res.string.formality_informal_low
    }
}
fun formalityToString(
    formality: Formality,
): String {
    return when (formality) {
        Formality.FORMAL_HIGH -> "formal_high"
        Formality.FORMAL_LOW ->  "formal_low"
        Formality.INFORMAL_LOW -> "informal_low"
    }
}

fun getFormalityFromString(
    formality: String,
): Formality {
    return when (formality) {
        "formal_high" -> Formality.FORMAL_HIGH
        "formal_low" -> Formality.FORMAL_LOW
        "informal_low" -> Formality.INFORMAL_LOW
        else -> Formality.FORMAL_HIGH
    }
}

