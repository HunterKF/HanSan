package com.jaegerapps.hansan.screens.practice.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "grammar"
)
data class GrammarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val tense: String,
    val formality: String,
    val selected: Boolean
)
