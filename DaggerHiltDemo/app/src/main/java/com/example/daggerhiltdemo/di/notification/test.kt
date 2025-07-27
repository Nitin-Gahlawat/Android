package com.example.daggerhiltdemo.di.notification
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat



fun sendNotification(
    context: Context,
    title: String,
    message: String,
    notificationId: Int,
    channelId: String,
    channelName: String
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    val notificationBuilder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .setDefaults(Notification.DEFAULT_ALL)

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(notificationId, notificationBuilder.build())
}
