package com.jaegerapps.hansan.common.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.jaegerapps.hansan.R
import com.jaegerapps.hansan.common.components.DAILY_REMINDER_CHANNEL
import com.jaegerapps.hansan.common.notification.time.NotificationTimeUseCase
import com.jaegerapps.hansan.common.notification.worker.ReminderWorker
import java.util.concurrent.TimeUnit

actual class KMPNotificationManager(private val context: Context) {
    actual fun showNotification(channelId: String, title: String, text: String, smallIcon: Int) {
        createNotificationChannel()
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentText(text)
            .setContentTitle(title)
            .setSmallIcon(smallIcon)
            .build()
        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DAILY_REMINDER_CHANNEL,
                DAILY_REMINDER_CHANNEL,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminder Channel Description"
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createWorkRequest(context: Context) {
        val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(
                NotificationTimeUseCase.getTimeBetweenTimeAndNine(),
                TimeUnit.SECONDS
            )
            .setInputData(
                workDataOf(
                    "title" to context.getString(R.string.app_name),
                    "message" to context.getString(R.string.notification_practice_today),
                )
            )
            .build()

        WorkManager.getInstance(context).enqueue(myWorkRequest)

    }

    actual fun scheduleNotification(
//        channelId: String,
//        title: String,
//        text: String,
//        smallIcon: Int,
    ) {
        createWorkRequest(context)
    }
}