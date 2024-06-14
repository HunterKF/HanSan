package com.jaegerapps.hansan.screens.onboarding.data.local.room

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity

interface LocalRoomDataSource {
    suspend fun addWords(list: List<WordEntity>)
    suspend fun addGrammar(grammarEntity: List<GrammarEntity>)
    suspend fun addTranslations(translationList: List<TranslationEntity>)
}