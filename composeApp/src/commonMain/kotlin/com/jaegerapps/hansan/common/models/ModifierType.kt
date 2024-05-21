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
}

@OptIn(ExperimentalResourceApi::class)
fun typeToStringResource(
    type: ModifierType,
): StringResource {
    return when (type) {
        ModifierType.VERBS ->  Res.string.type_verb
        ModifierType.ADJECTIVES -> Res.string.type_adjective
    }
}
fun typeToString(
    type: ModifierType,
): String {
    return when (type) {
        ModifierType.VERBS -> "verbs"
        ModifierType.ADJECTIVES -> "adjectives"
    }
}

fun stringToType(
    type: String,
): ModifierType {
    return when (type) {
        "verb" -> ModifierType.VERBS
        "adjective" -> ModifierType.ADJECTIVES
        else -> ModifierType.VERBS
    }
}