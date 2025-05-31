package com.example.serviceandbroadcast.services.exercise

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log

class ExerciseService: Service() {


    @SuppressLint("NewApi")
    private fun displayNotification(context: Context, title: String, message: String) {

        val channelId = "Message"
        val channelName = "Message_Channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder =
            Notification.Builder(context, channelId).setContentTitle(title).setContentText(message)
                .setSmallIcon(android.R.drawable.ic_dialog_info).setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("running","onStart service")
        displayNotification(this,"From Service","This is a notification from Service")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}