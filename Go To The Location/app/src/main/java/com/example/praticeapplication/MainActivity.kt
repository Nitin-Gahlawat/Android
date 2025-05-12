package com.example.praticeapplication

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        var ll =LinearLayout(this)
        ll.gravity=Gravity.CENTER
        ll.addView(Button(this).apply {
            text = "Welcome to My Application"
            gravity=Gravity.CENTER
            setOnClickListener{
                startActivity(Intent(this@MainActivity,ExplicitIntent::class.java))
            }
        })

        setContentView(ll)





    }
}