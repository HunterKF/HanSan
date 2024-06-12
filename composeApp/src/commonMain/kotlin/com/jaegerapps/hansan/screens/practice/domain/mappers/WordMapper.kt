package com.jaegerapps.hansan.screens.practice.domain.mappers

import com.jaegerapps.hansan.common.models.Word
import com.jaegerapps.hansan.common.models.getFormalityFromString
import com.jaegerapps.hansan.common.models.getTenseFromString
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.screens.practice.domain.models.toInt
import com.jaegerapps.hansan.screens.practice.domain.models.toLevel

fun Word.toWordEntity(): WordEntity {
    return WordEntity(
        word = conjugatedWord,
        level = level.toInt(),
        tense = tense.toString(),
        formality = formality.toString(),
        irregular = irregular,
        date_expiration = dateExpire ?: 0
    )
}

fun WordEntity.toWord(): Word {
    return Word(
        level = level.toLevel(),
        dateExpire = date_expiration,
        tense = getTenseFromString(tense),
        formality = getFormalityFromString(formality),
        conjugatedWord = word,
        irregular = irregular
    )
}

