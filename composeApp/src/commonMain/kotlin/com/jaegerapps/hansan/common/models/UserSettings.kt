package com.jaegerapps.hansan.common.models

data class UserSettings(
    val enabledTenses: List<Tense>,
    val enabledFormality: List<FormalityType>,
    val enableReminders: Boolean,
    val dailyTargetMax: Int,
    val currentPracticeDone: Int
)