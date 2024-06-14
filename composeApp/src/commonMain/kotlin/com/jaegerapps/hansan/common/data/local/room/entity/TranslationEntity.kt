package com.jaegerapps.hansan.common.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "translations"
)
data class TranslationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val reference_word: String,
    val language_code: String,
    val translation: String
)

