package com.jaegerapps.hansan.common.mappers

import com.jaegerapps.hansan.common.models.DefinitionTranslation
import com.jaegerapps.hansan.common.models.Formalities
import com.jaegerapps.hansan.common.models.FormalitiesDto
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.FutureDto
import com.jaegerapps.hansan.common.models.PastDto
import com.jaegerapps.hansan.common.models.PresentDto
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.TranslationDto
import com.jaegerapps.hansan.common.models.VerbDto
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.WordDto
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import kotlinx.serialization.json.Json

fun VerbDto.toVerbModel(): VerbModel {
    return VerbModel(
        baseWord = base,
        definitionTranslations = translationDto.map { it.toDefinitionTranslation() },
        formalities = formalitiesDto.toFormalities()
    )
}

fun TranslationDto.toDefinitionTranslation(): DefinitionTranslation {
    return DefinitionTranslation(
        languageCode = language_code,
        translation = language_translation
    )
}

fun FormalitiesDto.toFormalities(): Formalities {
    return Formalities(
        formalHigh = formal_high.toFormality(FormalityType.FORMAL_HIGH),
        formalLow = formal_low.toFormality(FormalityType.FORMAL_LOW),
        informalLow = informal_low.toFormality(FormalityType.INFORMAL_LOW)
    )
}

fun FormalityContainerDto.toFormality(type: FormalityType): Formality {
    return Formality(
        type = type,
        conjugation = listOf(
            this.conjugations.present.toWord(type),
            this.conjugations.past.toWord(type),
            this.conjugations.future.toWord(type)
        )
    )
}


fun PresentDto.toWord(type: FormalityType): Word {
    return declarative.toWord(Tense.PRESENT_DECLARATIVE, type)
}

fun PastDto.toWord(type: FormalityType): Word {
    return declarative.toWord(Tense.PRESENT_DECLARATIVE, type)
}

fun FutureDto.toWord(type: FormalityType): Word {
    return declarative.toWord(Tense.PRESENT_DECLARATIVE, type)
}

fun WordDto.toWord(tense: Tense, formality: FormalityType): Word {
    return Word(
        tense = tense,
        conjugatedWord = conjugated,
        irregular = irregular,
        dateExpire = null,
        formality = formality
        /*TODO - Figure out if I have to set the time here*/
    )
}

fun TenseEntity.toTenseModel(): TenseModel {
    return TenseModel(
        getTenseFromString(tense),
        getFormalityFromString(formality),
        conjugation,
        explanation,
        example_gada,
        example_boda,
        example_mokda,
        example_hada,
        irregularSieut,
        irregularDieut,
        irregularBieub,
        irregularEu,
        irregularReu,
        irregularRieul
    )
}

fun parseJsonWord(jsonString: String): List<VerbDto> {
    return Json.decodeFromString<List<VerbDto>>(jsonString)
}
