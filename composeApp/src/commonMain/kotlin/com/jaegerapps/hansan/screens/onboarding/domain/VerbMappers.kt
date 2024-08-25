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
    formalityContainer.conjugations.present.forEach {
        grammarEntities.add(GrammarEntity(0, category = "present", it.tense_name, formalityString, true))
    }
    formalityContainer.conjugations.future.forEach {
        grammarEntities.add(GrammarEntity(0, category = "future",it.tense_name, formalityString, true))
    }
    formalityContainer.conjugations.past.forEach {
        grammarEntities.add(GrammarEntity(0, category = "past",it.tense_name, formalityString, true))
    }
    formalityContainer.conjugations.other.forEach {
        grammarEntities.add(GrammarEntity(0, category = "other",it.tense_name, formalityString, false))
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
        present.forEach {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    it.tense_name,
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
        past.forEach {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    it.tense_name,
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
        future.forEach {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    it.tense_name,
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
        other.forEach {
            wordEntities.add(
                WordEntity(
                    0,
                    baseWord,
                    it.conjugated,
                    1,
                    it.tense_name,
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