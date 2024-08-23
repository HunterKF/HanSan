package com.jaegarapps.hansan.practice.domain

import com.jaegarapps.hansan.practice.data.MockLocalWordRoomDataSource
import com.jaegerapps.hansan.screens.practice.data.local.LocalWordRoomDataSource
import com.jaegerapps.hansan.screens.practice.domain.usecases.GetWordsByIdUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetWordsByIdUseCaseTest {

    private lateinit var mockLocalWordRoomDataSource: LocalWordRoomDataSource
    private lateinit var getWordsByIdUseCase: GetWordsByIdUseCase

    @BeforeTest
    fun setup() {
        mockLocalWordRoomDataSource = MockLocalWordRoomDataSource()
        getWordsByIdUseCase = GetWordsByIdUseCase(mockLocalWordRoomDataSource)
    }

    @Test
    fun `asdas`() = runBlocking {
        val words = getWordsByIdUseCase.invoke(
            1,
            tense = listOf(""),
            formality = listOf("")
        )
        println(words)
    }
}