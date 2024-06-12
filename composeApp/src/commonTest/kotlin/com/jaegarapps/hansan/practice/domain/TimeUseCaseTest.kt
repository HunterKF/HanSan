package com.jaegarapps.hansan.practice.domain

import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.t
import com.jaegerapps.hansan.screens.practice.domain.models.Level
import com.jaegerapps.hansan.screens.practice.domain.usecases.TimeUseCase
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeUseCaseTest {

    @Test
    fun LEVEL_ONE() {
        val fixedCurrentTime: LocalDateTime = LocalDateTime.parse("2024-05-26T12:30:28")
        val expected = 1716723028000
        val result = TimeUseCase.setTime(Level.LEVEL_ONE, fixedCurrentTime)
        Knower.t("LEVEL_ONE","Here is the result $result")
        assertEquals(expected, result)
    }
    @Test
    fun LEVEL_TWO() {
        val fixedCurrentTime: LocalDateTime = LocalDateTime.parse("2024-05-26T12:30:28")
        val expected = 1716744628000
        val result = TimeUseCase.setTime(Level.LEVEL_TWO, fixedCurrentTime)
        Knower.t("LEVEL_TWO","Here is the result $result")
        assertEquals(expected, result)
    }
    @Test
    fun LEVEL_THREE() {
        val fixedCurrentTime: LocalDateTime = LocalDateTime.parse("2024-05-26T12:30:28")
        val expected = 1716787828000
        val result = TimeUseCase.setTime(Level.LEVEL_THREE, fixedCurrentTime)
        Knower.t("LEVEL_THREE","Here is the result $result")
        assertEquals(expected, result)
    }
    @Test
    fun LEVEL_FOUR() {
        val fixedCurrentTime: LocalDateTime = LocalDateTime.parse("2024-05-26T12:30:28")
        val expected = 1716831028000
        val result = TimeUseCase.setTime(Level.LEVEL_FOUR, fixedCurrentTime)
        Knower.t("LEVEL_FOUR","Here is the result $result")
        assertEquals(expected, result)
    }
    @Test
    fun LEVEL_FIVE() {
        val fixedCurrentTime: LocalDateTime = LocalDateTime.parse("2024-05-26T12:30:28")
        val expected = 1716874228000
        val result = TimeUseCase.setTime(Level.LEVEL_FIVE, fixedCurrentTime)
        Knower.t("LEVEL_FIVE","Here is the result $result")
        assertEquals(expected, result)


    }
}