package com.jaegerapps.hansan.common.data.local

import com.jaegerapps.hansan.common.models.UserSettings
import com.jaegerapps.hansan.screens.practice.presentation.DailyGoal

expect class LocalSettings {
    fun resetDailyGoals()
}