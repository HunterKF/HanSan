package com.jaegerapps.hansan.screens.practice.data.local

import com.jaegerapps.hansan.common.data.local.room.dao.GrammarDao
import com.jaegerapps.hansan.common.data.local.room.dao.TranslationDao
import com.jaegerapps.hansan.common.data.local.room.dao.WordDao
import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity

class LocalWordRoomDataSourceImpl(
    private val wordDao: WordDao,
    private val translationDao: TranslationDao,
    private val grammarDao: GrammarDao
) : LocalWordRoomDataSource {

    override suspend fun getGrammar(): List<GrammarEntity> {
        return grammarDao.getSelectedGrammar()
    }

    override suspend fun getWordsByTime(
        time: Long,
        tense: List<String>,
        formality: List<String>,
    ): List<WordEntity> {
        return wordDao.getWordsByTime(tense, formality, time)

    }

    override suspend fun getWordsById(
        id: Int,
        tense: List<String>,
        formality: List<String>,
    ): List<WordEntity> {
        return wordDao.getWordsById(tense, formality, id)

    }

    override suspend fun getWords(tense: List<String>, formality: List<String>): List<WordEntity> {
        return wordDao.getWords(tense, formality)
    }

    override suspend fun getTranslation(baseWordList: List<String>): List<TranslationEntity> {
        return translationDao.getTranslationsByReferenceWord(baseWordList)
    }

    override suspend fun updateWord(wordEntity: WordEntity) {
        wordDao.updateWord(wordEntity)
    }

}