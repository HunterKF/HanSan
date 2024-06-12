package com.jaegerapps.hansan.screens.practice.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.TranslationEntity

@Dao
interface TranslationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTranslationList(list: List<TranslationEntity>)

    @Query("SELECT * FROM translations WHERE reference_word = :referenceWord")
    suspend fun getTranslationsByReferenceWord(referenceWord: String): List<TranslationEntity>

}