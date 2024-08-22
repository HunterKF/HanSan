package com.jaegerapps.hansan.common.notification.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.jaegerapps.hansan.R
import com.jaegerapps.hansan.common.notification.time.NotificationTimeUseCase
import java.util.concurrent.TimeUnit

class WorkUseCase {
    companion object {
        fun createWorkRequest(context: Context) {
            //This enqueues a reminder worker
            //The reminder worker enqueues the notification
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
    }
}