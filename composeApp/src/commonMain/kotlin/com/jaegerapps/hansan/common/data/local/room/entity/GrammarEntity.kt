package com.jaegerapps.hansan.common.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "grammar"
)
data class GrammarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: String,
    val tense: String,
    val formality: String,
    val selected: Boolean
)
