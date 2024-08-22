package com.jaegerapps.hansan.common.notification.time

import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.screens.practice.domain.usecases.TimeUseCase.Companion.plus
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until
import kotlin.time.Duration
import kotlin.time.DurationUnit

class NotificationTimeUseCase {
    companion object {
        fun getTimeBetweenTimeAndNine(): Long {
            val timezone = TimeZone.currentSystemDefault()
            val currentTime = Clock.System.now().toLocalDateTime(timezone)
            val targetTimeToday = LocalDateTime(currentTime.date, LocalTime(9, 0))
            val targetTime = if (currentTime < targetTimeToday) {
                targetTimeToday
            } else {
                // If the target time has passed, set the target time to 09:00 the next day
                targetTimeToday.plus(86400, DateTimeUnit.SECOND)
            }

            // Calculate the duration between the current time and the target time
            val duration = currentTime.toInstant(timezone).until(other = targetTime.toInstant(timezone), unit = DateTimeUnit.SECOND)
            Knower.d("NotificationTimeUseCase", "Here is the targetTime: ${targetTime}")
            Knower.d("NotificationTimeUseCase", "Here is the currentTime: ${currentTime}")

            return duration
        }
    }
}