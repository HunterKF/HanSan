package com.jaegerapps.hansan.common.notification.worker

import android.content.Context
import android.content.SharedPreferences
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jaegerapps.hansan.R
import com.jaegerapps.hansan.common.components.DAILY_REMINDER_CHANNEL
import com.jaegerapps.hansan.common.notification.KMPNotificationManager
import com.jaegerapps.hansan.common.util.SettingKeys
import com.russhwolf.settings.SharedPreferencesSettings

class ReminderWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        KMPNotificationManager(context).showNotification(
            channelId = DAILY_REMINDER_CHANNEL,
            inputData.getString("title").toString(),
            inputData.getString("message").toString(),
            smallIcon = R.drawable.ic_launcher_foreground
        )

        WorkUseCase.createWorkRequest(context)
        resetDailyMet()
        return Result.success()
    }

    private fun resetDailyMet() {
        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val settings = SharedPreferencesSettings(sharedPreferences)
        settings.putInt(SettingKeys.DAILY_TARGET_MET, 0)
    }
}