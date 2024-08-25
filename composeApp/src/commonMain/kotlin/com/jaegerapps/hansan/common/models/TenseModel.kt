package com.jaegerapps.hansan.common.models

import hansan.composeapp.generated.resources.Res
import hansan.composeapp.generated.resources.tense_desire
import hansan.composeapp.generated.resources.tense_future_declarative
import hansan.composeapp.generated.resources.tense_future_progressive
import hansan.composeapp.generated.resources.tense_imperative
import hansan.composeapp.generated.resources.tense_necessity
import hansan.composeapp.generated.resources.tense_past_declarative
import hansan.composeapp.generated.resources.tense_past_progressive
import hansan.composeapp.generated.resources.tense_permission
import hansan.composeapp.generated.resources.tense_potential
import hansan.composeapp.generated.resources.tense_present_declarative
import hansan.composeapp.generated.resources.tense_present_progressive
import hansan.composeapp.generated.resources.tense_prohibition
import hansan.composeapp.generated.resources.tense_propositive
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

data class TenseModel(
    val detailedTense: DetailedTense,
    val formalityType: FormalityType,
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

enum class DetailedTense {

    PRESENT_DECLARATIVE_FORMAL_HIGH,
    PRESENT_DECLARATIVE_FORMAL_LOW,
    PRESENT_DECLARATIVE_INFORMAL,
    PRESENT_PROGRESSIVE_FORMAL_HIGH,
    PRESENT_PROGRESSIVE_FORMAL_LOW,
    PRESENT_PROGRESSIVE_INFORMAL_LOW,
    PAST_DECLARATIVE_FORMAL_HIGH,
    PAST_DECLARATIVE_FORMAL_LOW,
    PAST_DECLARATIVE_INFORMAL,
    PAST_PROGRESSIVE_FORMAL_HIGH,
    PAST_PROGRESSIVE_FORMAL_LOW,
    PAST_PROGRESSIVE_INFORMAL,
    FUTURE_DECLARATIVE_FORMAL_HIGH,
    FUTURE_DECLARATIVE_FORMAL_LOW,
    FUTURE_DECLARATIVE_INFORMAL,
    FUTURE_PROGRESSIVE_FORMAL_HIGH,
    FUTURE_PROGRESSIVE_FORMAL_LOW,
    FUTURE_PROGRESSIVE_INFORMAL,
    OTHER_IMPERATIVE_FORMAL_HIGH,
    OTHER_IMPERATIVE_FORMAL_LOW,
    OTHER_IMPERATIVE_INFORMAL_LOW,
    OTHER_PERMISSION_FORMAL_HIGH,
    OTHER_PERMISSION_FORMAL_LOW,
    OTHER_PERMISSION_INFORMAL_LOW,
    OTHER_PROHIBITION_FORMAL_HIGH,
    OTHER_PROHIBITION_FORMAL_LOW,
    OTHER_PROHIBITION_INFORMAL_LOW,
    OTHER_DESIRE_FORMAL_HIGH,
    OTHER_DESIRE_FORMAL_LOW,
    OTHER_DESIRE_INFORMAL_LOW,
    OTHER_NECESSITY_FORMAL_HIGH,
    OTHER_NECESSITY_FORMAL_LOW,
    OTHER_NECESSITY_INFORMAL_LOW,
    OTHER_POTENTIAL_FORMAL_HIGH,
    OTHER_POTENTIAL_FORMAL_LOW,
    OTHER_POTENTIAL_INFORMAL_LOW,
    OTHER_PROPOSITIVE_FORMAL_HIGH,
    OTHER_PROPOSITIVE_FORMAL_LOW,
    OTHER_PROPOSITIVE_INFORMAL_LOW,
}

enum class Category {
    PRESENT,
    PAST,
    FUTURE,
    OTHER
}

fun getDetailedTenseFromString(value: String): DetailedTense {
    return when (value) {
        "present_declarative_formal_high" -> DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH
        "present_progressive_formal_high" -> DetailedTense.PRESENT_PROGRESSIVE_FORMAL_HIGH
        "propositive_formal_high" -> DetailedTense.OTHER_PROPOSITIVE_FORMAL_HIGH
        "present_declarative_formal_low" -> DetailedTense.PRESENT_DECLARATIVE_FORMAL_LOW
        "present_progressive_formal_low" -> DetailedTense.PRESENT_PROGRESSIVE_FORMAL_LOW
        "propositive_formal_low" -> DetailedTense.OTHER_PROPOSITIVE_FORMAL_LOW
        "present_declarative_informal" -> DetailedTense.PRESENT_DECLARATIVE_INFORMAL
        "present_progressive_informal_low" -> DetailedTense.PRESENT_PROGRESSIVE_INFORMAL_LOW
        "propositive_informal_low" -> DetailedTense.OTHER_PROPOSITIVE_INFORMAL_LOW
        "past_declarative_formal_high" -> DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH
        "past_progressive_formal_high" -> DetailedTense.PAST_PROGRESSIVE_FORMAL_HIGH
        "past_declarative_formal_low" -> DetailedTense.PAST_DECLARATIVE_FORMAL_LOW
        "past_progressive_formal_low" -> DetailedTense.PAST_PROGRESSIVE_FORMAL_LOW
        "past_declarative_informal" -> DetailedTense.PAST_DECLARATIVE_INFORMAL
        "past_progressive_informal_low" -> DetailedTense.PAST_PROGRESSIVE_INFORMAL
        "imperative_formal_high" -> DetailedTense.OTHER_IMPERATIVE_FORMAL_HIGH
        "permission_formal_high" -> DetailedTense.OTHER_PERMISSION_FORMAL_HIGH
        "prohibition_formal_high" -> DetailedTense.OTHER_PROHIBITION_FORMAL_HIGH
        "desire_formal_high" -> DetailedTense.OTHER_DESIRE_FORMAL_HIGH
        "necessity_formal_high" -> DetailedTense.OTHER_NECESSITY_FORMAL_HIGH
        "potential_formal_high" -> DetailedTense.OTHER_POTENTIAL_FORMAL_HIGH
        "imperative_formal_low" -> DetailedTense.OTHER_IMPERATIVE_FORMAL_LOW
        "permission_formal_low" -> DetailedTense.OTHER_PERMISSION_FORMAL_LOW
        "prohibition_formal_low" -> DetailedTense.OTHER_PROHIBITION_FORMAL_LOW
        "desire_formal_low" -> DetailedTense.OTHER_DESIRE_FORMAL_LOW
        "necessity_formal_low" -> DetailedTense.OTHER_NECESSITY_FORMAL_LOW
        "potential_formal_low" -> DetailedTense.OTHER_POTENTIAL_FORMAL_LOW
        "imperative_informal_low" -> DetailedTense.OTHER_IMPERATIVE_INFORMAL_LOW
        "permission_informal_low" -> DetailedTense.OTHER_PERMISSION_INFORMAL_LOW
        "prohibition_informal_low" -> DetailedTense.OTHER_PROHIBITION_INFORMAL_LOW
        "desire_informal_low" -> DetailedTense.OTHER_DESIRE_INFORMAL_LOW
        "necessity_informal_low" -> DetailedTense.OTHER_NECESSITY_INFORMAL_LOW
        "potential_informal_low" -> DetailedTense.OTHER_POTENTIAL_INFORMAL_LOW
        "future_declarative_formal_high" -> DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH
        "future_progressive_formal_high" -> DetailedTense.FUTURE_PROGRESSIVE_FORMAL_HIGH
        "future_declarative_formal_low" -> DetailedTense.FUTURE_DECLARATIVE_FORMAL_LOW
        "future_progressive_formal_low" -> DetailedTense.FUTURE_PROGRESSIVE_FORMAL_LOW
        "future_declarative_informal" -> DetailedTense.FUTURE_DECLARATIVE_INFORMAL
        "future_progressive_informal_low" -> DetailedTense.FUTURE_PROGRESSIVE_INFORMAL
        else -> DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH // Default value
    }
}

fun getStringFromTense(detailedTense: DetailedTense): String {
    return when (detailedTense) {
        DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH -> "present_declarative_formal_high"
        DetailedTense.PRESENT_PROGRESSIVE_FORMAL_HIGH -> "present_progressive_formal_high"
        DetailedTense.OTHER_PROPOSITIVE_FORMAL_HIGH -> "propositive_formal_high"
        DetailedTense.PRESENT_DECLARATIVE_FORMAL_LOW -> "present_declarative_formal_low"
        DetailedTense.PRESENT_PROGRESSIVE_FORMAL_LOW -> "present_progressive_formal_low"
        DetailedTense.OTHER_PROPOSITIVE_FORMAL_LOW -> "propositive_formal_low"
        DetailedTense.PRESENT_DECLARATIVE_INFORMAL -> "present_declarative_informal"
        DetailedTense.PRESENT_PROGRESSIVE_INFORMAL_LOW -> "present_progressive_informal_low"
        DetailedTense.OTHER_PROPOSITIVE_INFORMAL_LOW -> "propositive_informal_low"
        DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH -> "past_declarative_formal_high"
        DetailedTense.PAST_PROGRESSIVE_FORMAL_HIGH -> "past_progressive_formal_high"
        DetailedTense.PAST_DECLARATIVE_FORMAL_LOW -> "past_declarative_formal_low"
        DetailedTense.PAST_PROGRESSIVE_FORMAL_LOW -> "past_progressive_formal_low"
        DetailedTense.PAST_DECLARATIVE_INFORMAL -> "past_declarative_informal"
        DetailedTense.PAST_PROGRESSIVE_INFORMAL -> "past_progressive_informal_low"
        DetailedTense.OTHER_IMPERATIVE_FORMAL_HIGH -> "imperative_formal_high"
        DetailedTense.OTHER_PERMISSION_FORMAL_HIGH -> "permission_formal_high"
        DetailedTense.OTHER_PROHIBITION_FORMAL_HIGH -> "prohibition_formal_high"
        DetailedTense.OTHER_DESIRE_FORMAL_HIGH -> "desire_formal_high"
        DetailedTense.OTHER_NECESSITY_FORMAL_HIGH -> "necessity_formal_high"
        DetailedTense.OTHER_POTENTIAL_FORMAL_HIGH -> "potential_formal_high"
        DetailedTense.OTHER_IMPERATIVE_FORMAL_LOW -> "imperative_formal_low"
        DetailedTense.OTHER_PERMISSION_FORMAL_LOW -> "permission_formal_low"
        DetailedTense.OTHER_PROHIBITION_FORMAL_LOW -> "prohibition_formal_low"
        DetailedTense.OTHER_DESIRE_FORMAL_LOW -> "desire_formal_low"
        DetailedTense.OTHER_NECESSITY_FORMAL_LOW -> "necessity_formal_low"
        DetailedTense.OTHER_POTENTIAL_FORMAL_LOW -> "potential_formal_low"
        DetailedTense.OTHER_IMPERATIVE_INFORMAL_LOW -> "imperative_informal_low"
        DetailedTense.OTHER_PERMISSION_INFORMAL_LOW -> "permission_informal_low"
        DetailedTense.OTHER_PROHIBITION_INFORMAL_LOW -> "prohibition_informal_low"
        DetailedTense.OTHER_DESIRE_INFORMAL_LOW -> "desire_informal_low"
        DetailedTense.OTHER_NECESSITY_INFORMAL_LOW -> "necessity_informal_low"
        DetailedTense.OTHER_POTENTIAL_INFORMAL_LOW -> "potential_informal_low"
        DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH -> "future_declarative_formal_high"
        DetailedTense.FUTURE_PROGRESSIVE_FORMAL_HIGH -> "future_progressive_formal_high"
        DetailedTense.FUTURE_DECLARATIVE_FORMAL_LOW -> "future_declarative_formal_low"
        DetailedTense.FUTURE_PROGRESSIVE_FORMAL_LOW -> "future_progressive_formal_low"
        DetailedTense.FUTURE_DECLARATIVE_INFORMAL -> "future_declarative_informal"
        DetailedTense.FUTURE_PROGRESSIVE_INFORMAL -> "future_progressive_informal_low"
    }
}

@OptIn(ExperimentalResourceApi::class)
fun getTenseResString(value: DetailedTense): StringResource {
    return when (value) {
        DetailedTense.PRESENT_DECLARATIVE_FORMAL_HIGH -> Res.string.tense_present_declarative
        DetailedTense.PRESENT_PROGRESSIVE_FORMAL_HIGH -> Res.string.tense_present_progressive
        DetailedTense.OTHER_PROPOSITIVE_FORMAL_HIGH -> Res.string.tense_propositive
        DetailedTense.PRESENT_DECLARATIVE_FORMAL_LOW -> Res.string.tense_present_declarative
        DetailedTense.PRESENT_PROGRESSIVE_FORMAL_LOW -> Res.string.tense_present_progressive
        DetailedTense.OTHER_PROPOSITIVE_FORMAL_LOW -> Res.string.tense_propositive
        DetailedTense.PRESENT_DECLARATIVE_INFORMAL -> Res.string.tense_present_declarative
        DetailedTense.PRESENT_PROGRESSIVE_INFORMAL_LOW -> Res.string.tense_present_progressive
        DetailedTense.OTHER_PROPOSITIVE_INFORMAL_LOW -> Res.string.tense_propositive
        DetailedTense.PAST_DECLARATIVE_FORMAL_HIGH -> Res.string.tense_past_declarative
        DetailedTense.PAST_PROGRESSIVE_FORMAL_HIGH -> Res.string.tense_past_progressive
        DetailedTense.PAST_DECLARATIVE_FORMAL_LOW -> Res.string.tense_past_declarative
        DetailedTense.PAST_PROGRESSIVE_FORMAL_LOW -> Res.string.tense_past_progressive
        DetailedTense.PAST_DECLARATIVE_INFORMAL -> Res.string.tense_past_declarative
        DetailedTense.PAST_PROGRESSIVE_INFORMAL -> Res.string.tense_past_progressive
        DetailedTense.OTHER_IMPERATIVE_FORMAL_HIGH -> Res.string.tense_imperative
        DetailedTense.OTHER_PERMISSION_FORMAL_HIGH -> Res.string.tense_permission
        DetailedTense.OTHER_PROHIBITION_FORMAL_HIGH -> Res.string.tense_prohibition
        DetailedTense.OTHER_DESIRE_FORMAL_HIGH -> Res.string.tense_desire
        DetailedTense.OTHER_NECESSITY_FORMAL_HIGH -> Res.string.tense_necessity
        DetailedTense.OTHER_POTENTIAL_FORMAL_HIGH -> Res.string.tense_potential
        DetailedTense.OTHER_IMPERATIVE_FORMAL_LOW -> Res.string.tense_imperative
        DetailedTense.OTHER_PERMISSION_FORMAL_LOW -> Res.string.tense_permission
        DetailedTense.OTHER_PROHIBITION_FORMAL_LOW -> Res.string.tense_prohibition
        DetailedTense.OTHER_DESIRE_FORMAL_LOW -> Res.string.tense_desire
        DetailedTense.OTHER_NECESSITY_FORMAL_LOW -> Res.string.tense_necessity
        DetailedTense.OTHER_POTENTIAL_FORMAL_LOW -> Res.string.tense_potential
        DetailedTense.OTHER_IMPERATIVE_INFORMAL_LOW -> Res.string.tense_imperative
        DetailedTense.OTHER_PERMISSION_INFORMAL_LOW -> Res.string.tense_permission
        DetailedTense.OTHER_PROHIBITION_INFORMAL_LOW -> Res.string.tense_prohibition
        DetailedTense.OTHER_DESIRE_INFORMAL_LOW -> Res.string.tense_desire
        DetailedTense.OTHER_NECESSITY_INFORMAL_LOW -> Res.string.tense_necessity
        DetailedTense.OTHER_POTENTIAL_INFORMAL_LOW -> Res.string.tense_potential
        DetailedTense.FUTURE_DECLARATIVE_FORMAL_HIGH -> Res.string.tense_future_declarative
        DetailedTense.FUTURE_PROGRESSIVE_FORMAL_HIGH -> Res.string.tense_future_progressive
        DetailedTense.FUTURE_DECLARATIVE_FORMAL_LOW -> Res.string.tense_future_declarative
        DetailedTense.FUTURE_PROGRESSIVE_FORMAL_LOW -> Res.string.tense_future_progressive
        DetailedTense.FUTURE_DECLARATIVE_INFORMAL -> Res.string.tense_future_declarative
        DetailedTense.FUTURE_PROGRESSIVE_INFORMAL -> Res.string.tense_future_progressive
    }
}

/*@OptIn(ExperimentalResourceApi::class)
fun getStringFromTense(value: Tense): StringResource {
    return when (value) {
        Tense.PRESENT_DECLARATIVE ->
        Tense.PAST_DECLARATIVE ->
        Tense.FUTURE_DECLARATIVE ->
        Tense.PRESENT_DECLARATIVE_INQUISITIVE ->
        Tense.PRESENT_DECLARATIVE_NARRATIVE ->
        Tense.PRESENT_DECLARATIVE_SUGGESTIVE -> Res.string.tense_present_suggestive
        Tense.FUTURE_DECLARATIVE_FIRST_PERSON -> Res.string.tense_future_first_person
        Tense.PAST_DECLARATIVE_INQUISITIVE -> Res.string.tense_past_inquisitive
    }
}*/
