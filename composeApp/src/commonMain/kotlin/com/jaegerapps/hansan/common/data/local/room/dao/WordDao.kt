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
    @Query("SELECT * FROM words WHERE tense IN (:tense) AND formality IN (:formality) AND date_expiration < :time ORDER BY date_expiration LIMIT 20")
    suspend fun getWordsByTime(tense: List<String>, formality: List<String>, time: Long): List<WordEntity>

    @Query("SELECT * FROM words WHERE tense IN (:tense) AND formality IN (:formality) AND id > :id ORDER BY date_expiration LIMIT 20")
    suspend fun getWordsById(tense: List<String>, formality: List<String>, id: Int): List<WordEntity>

    @Query("SELECT * FROM words WHERE tense IN (:tense) AND formality IN (:formality) ORDER BY date_expiration")
    suspend fun getWords(tense: List<String>, formality: List<String>): List<WordEntity>
    @Update
    suspend fun updateWord(wordEntity: WordEntity)
}