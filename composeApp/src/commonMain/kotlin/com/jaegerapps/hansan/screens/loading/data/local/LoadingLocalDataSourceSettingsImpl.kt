package com.jaegerapps.hansan.screens.loading.data.local

import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import com.jaegerapps.hansan.common.util.SettingKeys
import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

class LoadingLocalDataSourceSettingsImpl(
    private val settings: Settings
): LoadingLocalDataSourceSettings {
    override suspend fun updateDailyTasks() {
        //Check if it's the same day, reset inputted goals to 0 if not
        if (!stillCurrentDay()) {
            settings.putInt(SettingKeys.DAILY_TARGET_MET, 0)
            Knower.d("updateDailyTasks", "Updating the daily entered.")
        }
    }

    private fun stillCurrentDay(): Boolean {
        val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val previousDate = settings.getStringOrNull(SettingKeys.LAST_DATE)
        Knower.d("stillCurrentDay", "Getting current date: $today")
        Knower.d("stillCurrentDay", "Getting previous date: $previousDate")

        if (previousDate == null) {
            settings.putString(SettingKeys.LAST_DATE, today.toString())
            return false
        }
        return previousDate == today.toString()
    }

}