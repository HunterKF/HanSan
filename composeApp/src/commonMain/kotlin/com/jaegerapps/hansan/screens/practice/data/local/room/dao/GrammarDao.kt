package com.jaegerapps.hansan.screens.practice.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.GrammarEntity

@Dao
interface GrammarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrammarForm(list: List<GrammarEntity>)

    @Query("SELECT * FROM grammar WHERE selected = true")
    suspend fun getSelectedGrammar(): List<GrammarEntity>

    @Update
    suspend fun updateGrammar(selectedGrammar: GrammarEntity)
}