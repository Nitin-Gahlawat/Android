package com.example.workmanagerandservice

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewService : Service() {

    @SuppressLint("NewApi")
    private fun makeNotification() {

        val chanelId = "Foreground Service ID"
        val channel = NotificationChannel(
            chanelId, chanelId, NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification: Notification.Builder = Notification.Builder(this, chanelId).apply {
            setContentText("Service is running")
            setContentTitle("Service enabled")
            setSmallIcon(android.R.drawable.ic_dialog_info)
            setAutoCancel(true)
        }
        startForeground(1001, notification.build())
    }

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onStartCommand(abc: Intent?, flags: Int, startId: Int): Int {

        Log.e("Servicenew", "starting a new service")

        scope.launch {
            var i = 0
            while (i<100) {
                Log.e("Service", "Service is running... $i")
                try {
                    App.flows.setSharedFlow(value = "The Shared count is $i")
                    delay(100)
                    App.flows.setStateFlow(value = "The State count is $i")
                    i++;
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        makeNotification()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}