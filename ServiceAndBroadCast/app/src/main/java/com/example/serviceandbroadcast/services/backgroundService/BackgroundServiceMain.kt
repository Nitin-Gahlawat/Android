package com.example.serviceandbroadcast.services.backgroundService

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.serviceandbroadcast.databinding.ActivityBackgroundServiceMainBinding




class BackgroundService: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                Log.e("Service", "Service is running...")
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}

class BackgroundServiceMain : AppCompatActivity() {
    private lateinit var binding:ActivityBackgroundServiceMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBackgroundServiceMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startService(Intent(this, BackgroundService::class.java))


    }
}