package com.example.landscapeeg

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text_key", textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredText = savedInstanceState.getString("text_key")
        if (restoredText != null) {
            textView.text = restoredText
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.ed_final)
        val sum_btn = findViewById<Button>(R.id.sum_btn)
        sum_btn.setOnClickListener {
            var text1: Int = findViewById<EditText>(R.id.value1).text.toString().toInt()
            var text2 = findViewById<EditText>(R.id.value2).text.toString().toInt()
            textView.text = ((text2 + text1).toString())
        }
    }
}