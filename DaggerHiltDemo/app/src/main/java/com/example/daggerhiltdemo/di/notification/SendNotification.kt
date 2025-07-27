package com.example.daggerhiltdemo.di.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext


// A class that requires a runtime parameter 'userId'
class NotificationManagerDagger @AssistedInject constructor(
    @Assisted("title") private val title: String,
    @Assisted("message") private val message: String,
    @Assisted("notificationId") private val notificationId: Int,
    @Assisted("channelId") private val channelId: String,
    @Assisted("channelName") private val channelName: String,
    @ApplicationContext private val context: Context
) {


    fun sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

//    fun requestNotificationPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            val notificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            if (ContextCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    context as Activity,
//                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//                    100
//                )
//            } else {
//                Toast.makeText(
//                    context,
//                    "Notification permission already granted",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        } else {
//            Toast.makeText(
//                context,
//                "Notification permission not required on this device",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

}


@AssistedFactory
interface NotificationManagerFactory {
    fun create(
        @Assisted("title") title: String,
        @Assisted("message") message: String,
        @Assisted("notificationId") notificationId: Int,
        @Assisted("channelId") channelId: String,
        @Assisted("channelName") channelName: String,
    ): NotificationManagerDagger
}
