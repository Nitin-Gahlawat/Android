package com.example.cafeapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY="Final_Order"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.order).setOnClickListener{
        val a:String= findViewById<EditText>(R.id.or1).text.toString()
        val b:String= findViewById<EditText>(R.id.or2).text.toString()
        val c:String= findViewById<EditText>(R.id.or3).text.toString()
        val d:String= findViewById<EditText>(R.id.or4).text.toString()

            val i:Intent=Intent(this,ConfirmOrder::class.java)
            i.putExtra(KEY,("$a \n$b \n$c \n$d"))
            startActivity(i)
        }

    }
}