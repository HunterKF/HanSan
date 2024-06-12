package com.jaegerapps.hansan.screens.practice.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "words"
)
data class WordEntity(
    @PrimaryKey
    val word: String,
    val level: Int,
    val tense: String,
    val formality: String,
    val irregular: Boolean,
    val date_expiration: Long
)
