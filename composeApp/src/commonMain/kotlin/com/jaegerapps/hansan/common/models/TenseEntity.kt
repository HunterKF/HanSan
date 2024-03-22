package com.jaegerapps.hansan.common.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TenseEntity(
    val tense: String,
    val formality: String,
    val conjugation: String,
    val explanation: String,
    val example_gada: String,
    val example_boda: String,
    val example_mokda: String,
    val example_hada: String,
)
fun parseJsonTense(jsonString: String): List<TenseEntity> {
    return Json.decodeFromString<List<TenseEntity>>(jsonString)
}
