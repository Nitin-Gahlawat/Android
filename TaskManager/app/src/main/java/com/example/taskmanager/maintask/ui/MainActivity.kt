package com.example.taskmanager.maintask.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private fun requestNotificationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    (this as Activity), arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1
                )
            } else {
                // Permission granted, proceed with notifications
                Toast.makeText(this, "Permission already granted!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun getMillis(): Long {
        val currentTime = Calendar.getInstance()
        val targetTime = Calendar.getInstance()

        targetTime.set(Calendar.HOUR_OF_DAY, 13)
        targetTime.set(Calendar.MINUTE, 16)
        targetTime.set(Calendar.SECOND, 0)
        targetTime.set(Calendar.MILLISECOND, 0)

        if (currentTime.after(targetTime)) {
            targetTime.add(Calendar.DAY_OF_YEAR, 1)
        }

        return targetTime.timeInMillis - currentTime.timeInMillis
    }


    @SuppressLint("NewApi")
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



    private val viewmodel: ViewModelClass by lazy {
        ViewModelProvider(this)[ViewModelClass::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_items, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.insert -> {
                var p = CreateTaskFragment()
                p.show(supportFragmentManager, "Testing")
                true
            }

            R.id.delete -> {
                viewmodel.deleteSelected()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        requestNotificationPermissions()
        requestExactAlarmPermission()

        setContentView(binding.root)
        binding.viewModel = viewmodel


        // Find the toolbar by its ID
        val toolbar = binding.topAppBar

        // Set the toolbar as the app's action bar
        setSupportActionBar(toolbar)

        binding.floatingActionButton.setOnClickListener {
            var p = CreateTaskFragment()
            p.show(supportFragmentManager, "Testing")
        }


//        setAlarm(getMillis())
    }
}


