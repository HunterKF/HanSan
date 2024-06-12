package com.jaegerapps.hansan.screens.practice.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.WordEntity

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(wordEntity: WordEntity)

    @Query("SELECT * FROM wordentity WHERE tense = :tense AND formality = :formality AND date_expiration < :time ORDER BY date_expiration")
    suspend fun getWords(tense: String, formality: String, time: Long): List<WordEntity>

    @Update
    suspend fun updateWord(wordEntity: WordEntity)
}