package com.jaegerapps.hansan.common.notification.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jaegerapps.hansan.R
import com.jaegerapps.hansan.common.components.DAILY_REMINDER_CHANNEL
import com.jaegerapps.hansan.common.notification.KMPNotificationManager

class ReminderWorker(private val context: Context, private val params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        KMPNotificationManager(context).showNotification(
            channelId = DAILY_REMINDER_CHANNEL,
            inputData.getString("title").toString(),
            inputData.getString("message").toString(),
            smallIcon = R.drawable.ic_launcher_foreground
        )
        WorkUseCase.createWorkRequest(context)
        return Result.success()
    }

}