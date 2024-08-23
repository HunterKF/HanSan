package com.jaegerapps.hansan.screens.practice.domain.mappers

import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.common.models.getStringFromFormality
import com.jaegerapps.hansan.common.models.getStringFromTense
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeTranslation
import com.jaegerapps.hansan.screens.practice.domain.models.PracticeWordModel
import com.jaegerapps.hansan.screens.practice.domain.models.toInt
import com.jaegerapps.hansan.screens.practice.domain.models.toLevel

fun PracticeWordModel.toWordEntity(): WordEntity {
    return WordEntity(
        id = id,
        base_word = baseWord,
        conjugated_word = conjugatedWord,
        level = level.toInt(),
        tense = getStringFromTense(tense),
        formality = getStringFromFormality(formality),
        irregular = irregular,
        date_expiration = dateExpire ?: 0
    )
}

fun WordEntity.toPracticeWordModel(translations: List<PracticeTranslation>?): PracticeWordModel {

    return PracticeWordModel(
        id = id,
        baseWord = base_word,
        translations = translations ?: emptyList(),
        level = level.toLevel(),
        dateExpire = date_expiration,
        tense = getTenseFromString(tense),
        formality = getFormalityFromString(formality),
        conjugatedWord = conjugated_word,
        irregular = irregular
    )
}

fun TranslationEntity.toPracticeTranslation(): PracticeTranslation {
    return PracticeTranslation(
        languageCode = this.language_code,
        translation = this.translation
    )
}


