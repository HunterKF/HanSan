package com.jaegerapps.hansan.screens.onboarding.data.local.room

import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.data.HanSanDataBase
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d

class LocalRoomDataSourceImpl(
    dataBase: HanSanDataBase
): LocalRoomDataSource {
    private val wordDao = dataBase.wordDao()
    private val translationDao = dataBase.translationDao()
    private val grammarDao = dataBase.grammarDao()
    override suspend fun addWords(list: List<WordEntity>) {
        Knower.d("addWords", "Words are being entered. Here is the list: $list")
        wordDao.insertAll(list)
    }

    override suspend fun addGrammar(grammarEntity: List<GrammarEntity>) {
        Knower.d("addGrammar", "Grammar is being entered. Here is the list: $grammarEntity")
        grammarDao.insertGrammarForm(grammarEntity)
    }

    override suspend fun addTranslations(translationList: List<TranslationEntity>) {
        Knower.d("addWords", "Translations are being entered. Here is the list: $translationList")
        translationDao.insertTranslationList(translationList)
    }


}