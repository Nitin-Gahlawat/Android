package com.example.customalarmclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MyAlarmReceiver : BroadcastReceiver() {


    fun generateVibrations(context: Context, pattern: ArrayList<Long>) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val effect = VibrationEffect.createWaveform(pattern.toLongArray(), -1)
                vibrator.vibrate(effect)
            } else {
                vibrator.vibrate(pattern.toLongArray(), -1)
            }
        }
    }

    fun showNotification(context: Context, channelId: String, title: String, message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "MyChannel", // Name of the channel
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        // Show the notification
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)  // 0 is the notification ID
    }

    override fun onReceive(con: Context?, p1: Intent?) {

        val notificationIntent = Intent(con, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(con, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        if (con != null) {
            showNotification(con, "helloworld", "this is a new Alarm ", "this is the message")


            Toast.makeText(con, "hello world", Toast.LENGTH_LONG).show()

            generateVibrations(con, arrayListOf(100, 200, 300, 500))

        }


    }



}
