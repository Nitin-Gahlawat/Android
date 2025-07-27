package com.example.workmanagerandservice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var shared = findViewById<TextView>(R.id.shared)
        var state = findViewById<TextView>(R.id.state)

        var job1 = lifecycleScope.launch {
            App.flows.getStateFlow().collect {
                state.text = it
            }
        }
        var job2 = lifecycleScope.launch {
            App.flows.getSharedFlow().collectLatest {
                shared.text = it
            }
        }


        var start =
            findViewById<com.google.android.material.button.MaterialButton>(R.id.startService)
        var stop = findViewById<com.google.android.material.button.MaterialButton>(R.id.stopService)

        start.setOnClickListener {
            startService(Intent(this, NewService::class.java))
        }
        stop.setOnClickListener {
            stopService(Intent(this, NewService::class.java))
        }
        lifecycleScope.launch {
                job1.join()
                job2.join()
                Log.d("values", "state ${App.flows.getStateFlow().value}")
        }


    }
}