package com.jaegerapps.hansan.common.notification

expect class KMPNotificationManager {
    fun showNotification(channelId: String, title: String, text: String, smallIcon: Int)
    fun scheduleNotification(/*channelId: String, title: String, text: String, smallIcon: Int*/)
}