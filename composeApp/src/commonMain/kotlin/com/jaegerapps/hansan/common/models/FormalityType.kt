package com.jaegerapps.hansan.common.models

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.formality_formal_high
import hansan.composeapp.generated.resources.formality_formal_low
import hansan.composeapp.generated.resources.formality_informal_low
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

enum class FormalityType {
    FORMAL_HIGH,
    FORMAL_LOW,
    INFORMAL_LOW,
}
@OptIn(ExperimentalResourceApi::class)
fun getResStringFromFormality(
    formalityType: FormalityType,
): StringResource {
    return when (formalityType) {
        FormalityType.FORMAL_HIGH -> Res.string.formality_formal_high
        FormalityType.FORMAL_LOW -> Res.string.formality_formal_low
        FormalityType.INFORMAL_LOW -> Res.string.formality_informal_low
    }
}
fun getStringFromFormality(
    formalityType: FormalityType,
): String {
    return when (formalityType) {
        FormalityType.FORMAL_HIGH -> "formal_high"
        FormalityType.FORMAL_LOW ->  "formal_low"
        FormalityType.INFORMAL_LOW -> "informal_low"
    }
}

fun getFormalityFromString(
    formality: String,
): FormalityType {
    return when (formality) {
        "formal_high" -> FormalityType.FORMAL_HIGH
        "formal_low" -> FormalityType.FORMAL_LOW
        "informal_low" -> FormalityType.INFORMAL_LOW
        else -> FormalityType.FORMAL_HIGH
    }
}

