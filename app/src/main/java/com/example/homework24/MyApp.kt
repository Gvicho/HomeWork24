package com.example.homework24

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        createChannels()
    }

    private fun createChannels() {
        val channel = NotificationChannel(
            "channel_id",
            "ImageUpload",
            NotificationManager.IMPORTANCE_HIGH
        )

        val channel1 = NotificationChannel(
            "channel_id1",
            "News Notification",
            NotificationManager.IMPORTANCE_HIGH
        )

        val manager: NotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        manager.createNotificationChannel(channel1)
    }

}