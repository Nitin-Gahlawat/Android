package com.example.serviceandbroadcast.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.serviceandbroadcast.R
import android.net.ConnectivityManager

class MainActivity : AppCompatActivity() {

    private lateinit var reciver: AirplaneModeChangeReceiver
    private lateinit var reciver2: NetworkChanged


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        reciver = AirplaneModeChangeReceiver()
        reciver2 = NetworkChanged()


        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(reciver, it)
        }

        IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).also {
            registerReceiver(reciver2, it)
        }


        Toast.makeText(this, "ready", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        this.unregisterReceiver(reciver)
        this.unregisterReceiver(reciver2)
    }
}