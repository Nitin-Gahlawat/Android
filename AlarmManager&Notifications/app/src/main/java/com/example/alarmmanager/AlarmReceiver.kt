package com.example.alarmmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat


class AlarmReceiver : BroadcastReceiver() {

    private fun showNotification(context: Context, title: String, body: String) {
        val builder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle("i is ${SetAlarm.i} $title")
            .setContentText("You have a new message. $body")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Channel Description"
            notificationManager.createNotificationChannel(channel)
        }
        Log.i("Notify", "$builder")
        notificationManager.notify(SetAlarm.i, builder.build())
    }

    override fun onReceive(context: Context, intent: Intent) {
            if (SetAlarm.i < 10) {
                var currpair = SetAlarm.androidFeatures[(SetAlarm.i)%6]
                SetAlarm.i += 1
                Log.i("Notify", "running the appbroadcast")
                showNotification(context, currpair.first, currpair.second)
                Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()
                SetAlarm.setAlarm(context, 5000)

            }

    }
}
