package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.common.data.local.room.entity.WordEntity
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource

class UpdateWordUseCase(
    private val localWordRoomDataSource: LocalWordRoomDataSource,
) {
    suspend fun invoke(wordEntity: WordEntity) {
        localWordRoomDataSource.updateWord(wordEntity)
    }
}