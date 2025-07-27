package com.example.buttongraphics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val canvas = findViewById<MyCanvas>(R.id.mycanvas)



        canvas.setOnClickListener {
            var job=lifecycleScope.launch(Dispatchers.Main) {
                if (canvas.isOn) {
                        canvas.turnOff()
                } else {
                    canvas.turnOn()
                }
            }
                job.start()
        }
    }

}
