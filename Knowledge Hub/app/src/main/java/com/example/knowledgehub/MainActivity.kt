package com.example.knowledgehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.lets_go).setOnClickListener{
            val i:Intent=Intent(this,KnowledgeFields::class.java)
            startActivity(i)
        }
    }
}