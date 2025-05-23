package com.example.alarmmanager

import android.Manifest
import android.app.AlarmManager
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {



    fun askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestExactAlarmPermission() {

        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (alarmManager.canScheduleExactAlarms()) {
            return
        } else {


            val explanationDialog = AlertDialog.Builder(this).setTitle("Permission Needed")
                .setMessage("This app requires permission to set exact alarms.")
                .setPositiveButton("OK") { _, _ ->
                    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                    startActivity(intent)
                }.setNegativeButton("Cancel", null).create()
            explanationDialog.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        askPermission()
        requestExactAlarmPermission()

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btn).setOnClickListener {
            SetAlarm.setAlarm(this,5000)
        }
    }





//    private fun getMillis(): Long {
//        val currentTime = Calendar.getInstance()
//        val targetTime = Calendar.getInstance()
//
//        targetTime.set(Calendar.HOUR_OF_DAY, 13)
//        targetTime.set(Calendar.MINUTE, 16)
//        targetTime.set(Calendar.SECOND, 0)
//        targetTime.set(Calendar.MILLISECOND, 0)
//
//        if (currentTime.after(targetTime)) {
//            targetTime.add(Calendar.DAY_OF_YEAR, 1)
//        }
//
//        return targetTime.timeInMillis - currentTime.timeInMillis
//    }
}