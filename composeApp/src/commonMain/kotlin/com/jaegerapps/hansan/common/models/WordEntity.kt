package com.jaegerapps.hansan.common.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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

fun parseJsonWord(jsonString: String): List<WordEntity> {
    return Json.decodeFromString<List<WordEntity>>(jsonString)
}
