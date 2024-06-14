package com.jaegerapps.hansan.screens.onboarding.domain

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.common.models.FormalityContainerDto
import com.jaegerapps.hansan.common.models.VerbEntity

fun VerbEntity.toGrammarEntities(): List<GrammarEntity> {
    val grammarEntities = mutableListOf<GrammarEntity>()

    // Iterate through formalities
    formalitiesEntity.apply {
        addGrammarEntitiesForFormality(formal_high, "Formal High", grammarEntities)
        addGrammarEntitiesForFormality(formal_low, "Formal Low", grammarEntities)
        addGrammarEntitiesForFormality(informal_low, "Informal Low", grammarEntities)
    }

    return grammarEntities
}

private fun addGrammarEntitiesForFormality(
    formalityContainer: FormalityContainerDto,
    formalityString: String,
    grammarEntities: MutableList<GrammarEntity>
) {
    // Iterate through tenses within each formality
    formalityContainer.conjugations.apply {
        grammarEntities.add(GrammarEntity(0, "Present", formalityString, true))
        grammarEntities.add(GrammarEntity(0, "Past", formalityString, true))
        grammarEntities.add(GrammarEntity(0, "Future", formalityString, true))
    }
}

fun VerbEntity.toWordEntities(): List<WordEntity> {
    val wordEntities = mutableListOf<WordEntity>()

    formalitiesEntity.apply {
        addWordEntitiesForFormality(formal_high, "Formal High", wordEntities)
        addWordEntitiesForFormality(formal_low, "Formal Low", wordEntities)
        addWordEntitiesForFormality(informal_low, "Informal Low", wordEntities)
    }

    return wordEntities
}

private fun addWordEntitiesForFormality(
    formalityContainer: FormalityContainerDto,
    formalityString: String,
    wordEntities: MutableList<WordEntity>
) {
    formalityContainer.conjugations.apply {
        present.declarative.let {
            wordEntities.add(
                WordEntity(
                    0,
                    it.conjugated,
                    1,
                    "Present",
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
                    it.conjugated,
                    1,
                    "Past",
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
                    it.conjugated,
                    1,
                    "Future",
                    formalityString,
                    it.irregular,
                    null
                )
            )
        }
    }
}

fun VerbEntity.toTranslationEntities(): List<TranslationEntity> {
    val translationEntities = mutableListOf<TranslationEntity>()

    // Assuming you only have English translations for now
    translationEntities.add(
        TranslationEntity(
            0, // ID will be auto-generated
            base, // The base form of the verb as the reference word
            "en", // Language code for English
            translationsEntity.english // The English translation
        )
    )

    return translationEntities
}