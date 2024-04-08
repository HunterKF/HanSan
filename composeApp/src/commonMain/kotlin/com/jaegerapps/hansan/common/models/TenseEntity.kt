package com.jaegerapps.hansan.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TenseEntity(
    val tense: String,
    val formality: String,
    val conjugation: String,
    val type: String,
    val explanation: String,
    val example_gada: String,
    val example_boda: String,
    val example_mokda: String,
    val example_hada: String,
    //ㅅ
    @SerialName("irregular_ㅅ") val irregularSieut: String?,
    //ㄷ
    @SerialName("irregular_ㄷ") val irregularDieut: String?,
    //ㅂ
    @SerialName("irregular_ㅂ")  val irregularBieub: String?,
    //ㅡ
    @SerialName("irregular_ㅡ") val irregularEu: String?,
    //르
    @SerialName("irregular_르") val irregularReu: String?,
    //ㄹ
    @SerialName("irregular_ㄹ")  val irregularRieul: String?,
)
fun parseJsonTense(jsonString: String): List<TenseEntity> {
    return Json.decodeFromString<List<TenseEntity>>(jsonString)
}
