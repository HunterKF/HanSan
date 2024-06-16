package com.jaegerapps.hansan.common.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity

@Dao
interface TranslationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslationList(list: List<TranslationEntity>)

    @Query("SELECT * FROM translations WHERE reference_word IN (:referenceWordList)")
    suspend fun getTranslationsByReferenceWord(referenceWordList: List<String>): List<TranslationEntity>

}