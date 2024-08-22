package com.jaegarapps.hansan.common.time

import com.jaegerapps.hansan.common.notification.time.NotificationTimeUseCase
import kotlin.test.Test

class NotificationTimeUseCaseTest {

    @Test
    fun `Test Time`() {
        val time = NotificationTimeUseCase.getTimeBetweenTimeAndNine()
        println(time)
    }
}