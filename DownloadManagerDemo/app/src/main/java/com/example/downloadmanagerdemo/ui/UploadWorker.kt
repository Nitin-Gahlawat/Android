package com.example.downloadmanagerdemo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException
import java.io.InputStream


class DownloadWorker(var context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {


    lateinit var URL: String
    private lateinit var distination: String

    private lateinit var fileLoc: File
    private lateinit var filename: String


    fun getUriFromFile(context: Context, filePath: String): Uri? {
        val file = File(filePath)
        return try {
            val contentUri = if (file.exists()) {
                MediaStore.Files.getContentUri("external")
            } else {
                null
            }
            contentUri?.let {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DATA, file.absolutePath)
                }
                context.contentResolver.insert(it, contentValues)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {

        URL = inputData.getString(MainActivity.URL).toString()
        distination = inputData.getString(MainActivity.distination).toString()
        filename = URL.split("/").last()
        fileLoc = File(distination, filename)


        downloadImage(URL)
        return Result.success()
    }


    fun writefile(p: InputStream?) {


        sendNotification(context, "Download Manager", "Your item $filename download is complete")

        p?.readBytes()?.let { fileLoc.writeBytes(it) }

    }

    private fun downloadImage(imageUrl: String) {
        val client = OkHttpClient()

        val request = Request.Builder().url(imageUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                Toast.makeText(context, "Failed to load image", Toast.LENGTH_SHORT).show()
                Log.d("theerror", "Failed to load image")
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val inputStream = response.body?.byteStream()
                    writefile(inputStream)

                } else {
                    Log.d("theerror", "Error: ${response.message}")
                    Toast.makeText(
                        context, "Error: ${response.message}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


    private fun sendNotification(context: Context, title: String, message: String) {
        val channelId = "default_channel_id"
        val channelName = "Default Channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "This is the default notification channel"
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }
}