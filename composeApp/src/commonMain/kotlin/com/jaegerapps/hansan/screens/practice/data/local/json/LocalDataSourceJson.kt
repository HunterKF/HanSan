package com.jaegerapps.hansan.screens.practice.data.local.json

import com.jaegerapps.hansan.screens.practice.data.local.room.entity.WordEntity

interface LocalDataSourceJson {
    suspend fun getWords(cursor: Int?, pageSize: Int): List<WordEntity>
}