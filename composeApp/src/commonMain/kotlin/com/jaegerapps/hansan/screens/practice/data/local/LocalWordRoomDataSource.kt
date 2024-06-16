package com.jaegerapps.hansan.screens.practice.data.local

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity

interface LocalWordRoomDataSource {
    suspend fun getGrammar(): List<GrammarEntity>
    suspend fun getWordsByTime(time: Long, tense: List<String>, formality: List<String>): List<WordEntity>
    suspend fun getWordsById(id: Int, tense: List<String>, formality: List<String>): List<WordEntity>
    suspend fun getWords(tense: List<String>, formality: List<String>): List<WordEntity>
    suspend fun getTranslation(baseWordList: List<String>): List<TranslationEntity>
    suspend fun updateWord(wordEntity: WordEntity)
}