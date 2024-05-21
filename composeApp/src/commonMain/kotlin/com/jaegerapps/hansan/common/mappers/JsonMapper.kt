package com.jaegerapps.hansan.common.mappers

import com.jaegerapps.hansan.common.models.DefinitionTranslations
import com.jaegerapps.hansan.common.models.Formalities
import com.jaegerapps.hansan.common.models.FormalitiesEntity
import com.jaegerapps.hansan.common.models.Formality
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.FormalityType
import com.jaegerapps.hansan.common.models.FutureDto
import com.jaegerapps.hansan.common.models.PastDto
import com.jaegerapps.hansan.common.models.PresentDto
import com.jaegerapps.hansan.common.models.Tense
import com.jaegerapps.hansan.common.models.TenseEntity
import com.jaegerapps.hansan.common.models.TenseModel
import com.jaegerapps.hansan.common.models.TranslationsEntity
import com.jaegerapps.hansan.common.models.VerbEntity
import com.jaegerapps.hansan.common.models.VerbModel
import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.WordDto
import com.jaegerapps.hansan.common.models.WordTenseModel
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.common.models.stringToType
import kotlinx.serialization.json.Json

fun VerbEntity.toVerbModel(): VerbModel {
    return VerbModel(
        baseWord = base,
        definitionTranslations = translationsEntity.toDefinitionTranslation(),
        formalities = formalitiesEntity.toFormalities()
    )
}

fun TranslationsEntity.toDefinitionTranslation(): DefinitionTranslations {
    return DefinitionTranslations(
        english = this.english
    )
}

fun FormalitiesEntity.toFormalities(): Formalities {
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
            this.conjugation.present.toWord(),
            this.conjugation.past.toWord(),
            this.conjugation.future.toWord()
        )
    )
}


fun PresentDto.toWord(): Word {
    return declarative.toWord(Tense.PRESENT_DECLARATIVE)
}

fun PastDto.toWord(): Word {
    return declarative.toWord(Tense.PAST_DECLARATIVE)
}

fun FutureDto.toWord(): Word {
    return declarative.toWord(Tense.FUTURE_DECLARATIVE)
}

fun WordDto.toWord(tense: Tense): Word {
    return Word(
        tense = tense,
        conjugatedWord = conjugated,
        irregular = irregular
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

fun parseJsonWord(jsonString: String): List<VerbEntity> {
    return Json.decodeFromString<List<VerbEntity>>(jsonString)
}
