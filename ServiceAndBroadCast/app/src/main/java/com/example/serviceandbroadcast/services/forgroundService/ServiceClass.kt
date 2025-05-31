package com.example.serviceandbroadcast.services.forgroundService

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.RestrictTo
import com.example.serviceandbroadcast.services.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ForegroundService : Service() {

    // private mutable shared flow


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
//            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }

        Log.d("started", "acceptable")
        startForeground(1001, notification.build())
    }

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onStartCommand(abc: Intent?, flags: Int, startId: Int): Int {

        Log.e("Servicenew", "starting a new service")

        scope.launch {
            var i = 0
            while (true) {
                Log.e("Service", "Service is running... $i")
                try {
                    delay(2000)
                    App.serviceHandlerInterface.setValue("The count is $i")
                    i++;
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

//        Send a notification
        makeNotification()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d("sadfasf", "onDestroy: sdg")

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}