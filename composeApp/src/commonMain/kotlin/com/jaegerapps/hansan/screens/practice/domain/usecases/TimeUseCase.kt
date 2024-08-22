package com.jaegerapps.hansan.screens.practice.domain.usecases

import com.jaegerapps.hansan.screens.practice.domain.models.Level
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.ZoneOffset
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class TimeUseCase {

    companion object {
        fun setTime(level: Level, datetime: LocalDateTime): Long {
            return when (level) {
                Level.LEVEL_ONE -> datetime.plus(6, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_TWO -> datetime.plus(12, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_THREE -> datetime.plus(24, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_FOUR -> datetime.plus(36, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_FIVE -> datetime.plus(48, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_SIX -> datetime.plus(60, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_SEVEN -> datetime.plus(72, DateTimeUnit.HOUR).toEpochSeconds()
                Level.LEVEL_EIGHT -> datetime.plus(84, DateTimeUnit.HOUR).toEpochSeconds()
            }
        }
        fun LocalDateTime.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
            val timeZone = TimeZone.currentSystemDefault()
            return this.toInstant(timeZone)
                .plus(value, unit)
                .toLocalDateTime(timeZone)
        }

        private fun LocalDateTime.toEpochSeconds(): Long {
            return this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        }

        fun longToDate(nanoseconds: Long): LocalDateTime {
            val instance = Instant.fromEpochMilliseconds(nanoseconds).toLocalDateTime(TimeZone.currentSystemDefault())
            return instance

        }


    }
}
