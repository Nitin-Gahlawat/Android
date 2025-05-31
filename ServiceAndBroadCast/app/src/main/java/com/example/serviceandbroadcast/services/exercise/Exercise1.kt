package com.example.serviceandbroadcast.services.exercise

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.serviceandbroadcast.databinding.ActivityExercise1Binding

class Exercise1 : AppCompatActivity() {
    companion object{
        var started=0
    }

    fun RequestNotification(context: Context) {
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

    lateinit var binding: ActivityExercise1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RequestNotification(this)
        binding = ActivityExercise1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startNotification.setOnClickListener{
            startService(Intent(this, ExerciseService::class.java))
        }


    }
}