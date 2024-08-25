package com.jaegerapps.hansan.common.mappers

import com.jaegerapps.hansan.common.models.DefinitionTranslation
import com.jaegerapps.hansan.common.models.Formalities
import com.jaegerapps.hansan.common.models.FormalitiesDto
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.DetailedTense
import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.TranslationDto
import com.jaegerapps.hansan.common.models.VerbDto
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.WordDto
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getDetailedTenseFromString
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
        conjugation = this.conjugations.present.map {
            it.toWord(
                detailedTense = getDetailedTenseFromString(it.tense_name),
                formality = type
            )
        } + this.conjugations.past.map {
            it.toWord(
                detailedTense = getDetailedTenseFromString(it.tense_name),
                formality = type
            )
        } + this.conjugations.future.map { it.toWord(
            detailedTense = getDetailedTenseFromString(it.tense_name),
            formality = type
        ) }+ this.conjugations.other.map { it.toWord(
            detailedTense = getDetailedTenseFromString(it.tense_name),
            formality = type
        ) }
    )
}


fun WordDto.toWord(detailedTense: DetailedTense, formality: FormalityType): Word {
    return Word(
        detailedTense = detailedTense,
        conjugatedWord = conjugated,
        irregular = irregular,
        dateExpire = null,
        formality = formality
        /*TODO - Figure out if I have to set the time here*/
    )
}

fun TenseEntity.toTenseModel(): TenseModel {
    return TenseModel(
        getDetailedTenseFromString(tense),
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
