package com.example.cafeapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class ConfirmOrder : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm_order)
        val i: Intent =intent
        val ord=i.getStringExtra(MainActivity.KEY)

        findViewById<TextView>(R.id.orderdisplay).text=ord

        findViewById<AppCompatButton>(R.id.ConfirmOrd).setOnClickListener{
            var t=Toast.makeText(this,"order is Confirmed",Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

    }
}