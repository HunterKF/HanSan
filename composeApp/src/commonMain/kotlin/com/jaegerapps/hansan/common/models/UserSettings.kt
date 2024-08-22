package com.jaegerapps.hansan.common.models

data class UserSettings(
    val enableReminders: Boolean,
    val dailyTargetMax: Int,
    val currentPracticeDone: Int
)