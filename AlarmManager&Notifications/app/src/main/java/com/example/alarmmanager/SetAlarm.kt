package com.example.alarmmanager


import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.widget.Toast


class SetAlarm {
    companion object {
        var i=0

        var androidFeatures: ArrayList<Pair<String, String>> = arrayListOf(
            Pair("Donut", "Cupcake"),
            Pair("Eclair", "Froyo"),
            Pair("Gingerbread", "Honeycomb"),
            Pair("Sandwich", "Jelly Bean"),
            Pair("Key Lime Pie", "Lemon Meringue Pie"),
            Pair("Macadamia Nut Cookie", "New York Cheesecake"),
        )

        fun setAlarm(context: Context, timeInMillis: Long) {
            var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, FLAG_IMMUTABLE)

            try {
                val triggerTime = System.currentTimeMillis() + timeInMillis

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                Toast.makeText(context, "okokokokok", Toast.LENGTH_SHORT).show()
            } catch (e: SecurityException) {
            }

            Toast.makeText(context, "Alarm set for PM", Toast.LENGTH_SHORT).show()
        }
    }
}