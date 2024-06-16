package com.jaegerapps.hansan.screens.onboarding.domain

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.TranslationDto
import com.jaegerapps.hansan.common.models.VerbDto

fun VerbDto.toGrammarEntities(): List<GrammarEntity> {
    val grammarEntities = mutableListOf<GrammarEntity>()

    // Iterate through formalities
    formalitiesDto.apply {
        addGrammarEntitiesForFormality(formal_high, "formal_high", grammarEntities)
        addGrammarEntitiesForFormality(formal_low, "formal_low", grammarEntities)
        addGrammarEntitiesForFormality(informal_low, "informal_low", grammarEntities)
    }

    return grammarEntities
}



private fun addGrammarEntitiesForFormality(
    formalityContainer: FormalityContainerDto,
    formalityString: String,
    grammarEntities: MutableList<GrammarEntity>,
) {
    // Iterate through tenses within each formality
    formalityContainer.conjugations.apply {
        grammarEntities.add(GrammarEntity(0, "present_declarative", formalityString, true))
        grammarEntities.add(GrammarEntity(0, "past_declarative", formalityString, true))
        grammarEntities.add(GrammarEntity(0, "future_declarative", formalityString, true))
    }
}

fun VerbDto.toWordEntities(): List<WordEntity> {
    val wordEntities = mutableListOf<WordEntity>()

    formalitiesDto.apply {
        addWordEntitiesForFormality(formal_high, "formal_high", wordEntities, base)
        addWordEntitiesForFormality(formal_low, "formal_low", wordEntities, base)
        addWordEntitiesForFormality(informal_low, "informal_low", wordEntities, base)
    }

    return wordEntities
}

private fun addWordEntitiesForFormality(
    formalityContainer: FormalityContainerDto,
    formalityString: String,
    wordEntities: MutableList<WordEntity>,
    baseWord: String
) {
    formalityContainer.conjugations.apply {
        present.declarative.let {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    "present_declarative",
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
        past.declarative.let {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    "past_declarative",
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
        future.declarative.let {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    "future_declarative",
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
    }
}

fun VerbDto.toTranslationEntities(): List<TranslationEntity> {
    return this.translationDto.map { it.toTranslationEntity(base) }
}

fun TranslationDto.toTranslationEntity(referenceWord: String): TranslationEntity {
    return TranslationEntity(
        id = 0,
        reference_word = referenceWord,
        language_code = this.language_code,
        translation = language_translation
    )
}