package com.jaegerapps.hansan.common.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(words: List<WordEntity>)

    @Query("SELECT * FROM words WHERE tense = :tense AND formality = :formality AND date_expiration < :time ORDER BY date_expiration")
    suspend fun getWordsByTime(tense: String, formality: String, time: Long): List<WordEntity>

    @Query("SELECT * FROM words ORDER BY date_expiration")
    suspend fun getWords(): List<WordEntity>
    @Update
    suspend fun updateWord(wordEntity: WordEntity)
}