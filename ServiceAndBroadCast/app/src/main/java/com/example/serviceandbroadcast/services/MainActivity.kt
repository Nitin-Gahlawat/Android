package com.example.serviceandbroadcast.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serviceandbroadcast.databinding.ActivityMainBinding
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private fun requestNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (context is Activity) {
                    ActivityCompat.requestPermissions(
                        context, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1
                    )
                }
                return
            }
        }
    }


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

        // Build and display the notification
        val notificationBuilder =
            Notification.Builder(context, channelId).setContentTitle(title).setContentText(message)
                .setSmallIcon(android.R.drawable.ic_dialog_info).setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }


    var isStarted: Boolean = false


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)


        requestNotification(this)

        binding.start.setOnClickListener {
            displayNotification(
                this, "New", "service Started"
            )
            if (!isStarted) {
                startService(Intent(this, MyService::class.java))
                isStarted=true
            }

        }

        binding.stop.setOnClickListener {
            if(isStarted) {
                stopService(Intent(this, MyService::class.java))
                isStarted=false
            }

        }

    }
}