package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class NewsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_details)
        val int:Intent= intent
        findViewById<TextView>(R.id.headDisplay).text=int.getStringExtra("heading")
        findViewById<TextView>(R.id.bodyDisplay).text=int.getStringExtra("body")
        findViewById<ImageView>(R.id.imgDisplay).setBackgroundResource(int.getIntExtra("img",R.drawable.img1))

    }
}