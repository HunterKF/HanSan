package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.data.local.room.entity.TranslationEntity
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource

class GetTranslationsUseCase(
    private val localWordRoomDataSource: LocalWordRoomDataSource
) {
    suspend fun invoke(baseWordList: List<String>): List<TranslationEntity> {
        return localWordRoomDataSource.getTranslation(baseWordList)
    }
}