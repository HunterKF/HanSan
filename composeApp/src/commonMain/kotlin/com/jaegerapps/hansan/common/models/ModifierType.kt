package com.jaegerapps.hansan.common.models

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.tense_past
import hansan.composeapp.generated.resources.type_adjective
import hansan.composeapp.generated.resources.type_adverb
import hansan.composeapp.generated.resources.type_verb
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

enum class ModifierType {
    VERBS,
    ADJECTIVES,
    ADVERBS
}

@OptIn(ExperimentalResourceApi::class)
fun typeToStringResource(
    type: ModifierType,
): StringResource {
    return when (type) {
        ModifierType.VERBS ->  Res.string.type_verb
        ModifierType.ADJECTIVES -> Res.string.type_adjective
        ModifierType.ADVERBS -> Res.string.type_adverb
    }
}
fun typeToString(
    type: ModifierType,
): String {
    return when (type) {
        ModifierType.VERBS -> "verbs"
        ModifierType.ADJECTIVES -> "adjectives"
        ModifierType.ADVERBS -> "adverbs"
    }
}

fun stringToType(
    type: String,
): ModifierType {
    return when (type) {
        "verbs" -> ModifierType.VERBS
        "adjectives" -> ModifierType.ADJECTIVES
        "adverbs" -> ModifierType.ADVERBS
        else -> ModifierType.VERBS
    }
}