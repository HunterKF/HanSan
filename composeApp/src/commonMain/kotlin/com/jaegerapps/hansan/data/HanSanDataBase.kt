package com.jaegerapps.hansan.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jaegerapps.hansan.screens.practice.data.local.room.dao.GrammarDao
import com.jaegerapps.hansan.screens.practice.data.local.room.dao.TranslationDao
import com.jaegerapps.hansan.screens.practice.data.local.room.dao.WordDao
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.GrammarEntity
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.screens.practice.data.local.room.entity.WordEntity

@Database(entities = [WordEntity::class, TranslationEntity::class, GrammarEntity::class], version = 3)
abstract class HanSanDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun grammarDao(): GrammarDao
    abstract fun translationDao(): TranslationDao
}