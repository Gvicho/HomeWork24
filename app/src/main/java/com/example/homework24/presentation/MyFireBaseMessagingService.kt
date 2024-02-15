package com.example.homework24.presentation

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.homework24.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val data = message.data
        Log.d("tag123", "Message data payload: ${message.data}")
        val postId = data["postId"]?.toIntOrNull() // Make sure the key matches what's being sent from FCM
        showNotification(title = data["title"] ?: "", desc = data["desc"] ?: "", postId = postId)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle token update
    }

    private fun showNotification(title: String, desc: String, postId: Int?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Log.d("tag123","from the service :::::  postId = $postId")
            if (postId != null) {
                putExtra("postId", postId) // Include the postId in the intent
            }
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        val notification: Notification = NotificationCompat.Builder(applicationContext, "channel_id1")
            .setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()


        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(applicationContext)
                .notify(1, notification)
        }
    }
}
