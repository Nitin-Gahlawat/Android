package com.example.customalarmclock

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Calendar


class MainActivity : AppCompatActivity() {



//    Create a readme file for the Custom clock using alarm manager which give toast,notification and Vibration on completion of time in andorid kotlin In markdown format
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1
                )
            } else {
                Toast.makeText(this, "Notification permission already granted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun getTimeLeftInMillis(targetHours: Int, targetMinutes: Int): Long {

        val currentTime = System.currentTimeMillis()

        val currentCalendar: Calendar = Calendar.getInstance()

        currentCalendar.set(Calendar.HOUR_OF_DAY, targetHours)
        currentCalendar.set(Calendar.MINUTE, targetMinutes)
        var targetTime: Long = currentCalendar.timeInMillis
        if (targetTime <= currentTime) {
            currentCalendar.add(Calendar.DATE, 1)
            targetTime = currentCalendar.timeInMillis
        }
        return targetTime - currentTime
    }

    val alarmPermissionResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("Alarm permissions ", ": $result")
    }


    @SuppressLint("ScheduleExactAlarm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {


            val hasExactAlarmPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.canScheduleExactAlarms()
            } else {
                true
            }

            if (!hasExactAlarmPermission) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                val uri = Uri.fromParts("package", packageName, null)
                intent.setData(uri)
                alarmPermissionResultLauncher.launch(intent)
            }
        }

        val intent = Intent(this, MyAlarmReceiver::class.java)

        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        requestNotificationPermission()
        findViewById<com.google.android.material.button.MaterialButton>(R.id.setTime).setOnClickListener {
            var time = findViewById<TimePicker>(R.id.timepicker)

            val alarmManager =
                this@MainActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + getTimeLeftInMillis(time.hour, time.minute),
                pendingIntent
            )

            var text = "The time is set for ${time.hour}:${time.minute}"

            Toast.makeText(
                this@MainActivity,
                text,
                Toast.LENGTH_LONG
            ).show()

        }
    }
}