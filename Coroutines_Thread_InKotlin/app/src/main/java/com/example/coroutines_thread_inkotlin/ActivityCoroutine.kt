package com.example.coroutines_thread_inkotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ActivityCoroutine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corutine)


        runBlocking {
            CoroutineScope(Dispatchers.Main.immediate).launch {
                delay(2000)
                findViewById<TextView>(R.id.greet).text = "This is Kotlin"
//            println("Running on the main thread")
            }
        }

//
//        lifecycleScope.launch{
//            delay(2000)
//            findViewById<TextView>(R.id.greet).text="This is Kotlin"
//        }
    }
}