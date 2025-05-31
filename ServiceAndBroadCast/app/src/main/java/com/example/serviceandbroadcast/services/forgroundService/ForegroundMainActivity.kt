package com.example.serviceandbroadcast.services.forgroundService

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.serviceandbroadcast.databinding.ActivityForgroundServiceMainBinding
import com.example.serviceandbroadcast.services.App
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ForegroundMainActivity : AppCompatActivity() {

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

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = manager.getRunningServices(Int.MAX_VALUE)
        for (service in services) {
            if (service.service.className == serviceClass.name) {
                return true
            }
        }
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityForgroundServiceMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            App.serviceHandlerInterface.getValue().collectLatest {
                Log.d("collecting", "text is $it")
                binding.displayCount.setText(it)
            }
        }



        requestNotification(this)
        binding.makenotify.setOnClickListener {
            if (!isServiceRunning(ForegroundService::class.java)) {
                startForegroundService(
                    Intent(
                        this, ForegroundService::class.java
                    )
                )
            } else {
                Toast.makeText(this, "service is already running", Toast.LENGTH_LONG).show()
            }
        }

        binding.stopService.setOnClickListener {
            Toast.makeText(this, "Stopping service form ", Toast.LENGTH_LONG).show()
            stopService(
                Intent(this, ForegroundService::class.java)
            )
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}