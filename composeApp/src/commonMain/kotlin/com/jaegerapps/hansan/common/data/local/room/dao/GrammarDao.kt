package com.jaegerapps.hansan.common.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaegerapps.hansan.common.data.local.room.entity.GrammarEntity

@Dao
interface GrammarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrammarForm(list: List<GrammarEntity>)

    @Query("SELECT * FROM grammar WHERE selected = true")
    suspend fun getSelectedGrammar(): List<GrammarEntity>
    @Query("UPDATE grammar SET selected = :isSelected WHERE formality = :formality AND tense IN (:tenses)")
    suspend fun updateFormality(formality: String, tenses: List<String>, isSelected: Boolean)

    @Query("UPDATE grammar SET selected = :isSelected WHERE tense = :tense AND formality IN (:formalities)")
    suspend fun updateTense(tense: String, formalities: List<String>, isSelected: Boolean)
}