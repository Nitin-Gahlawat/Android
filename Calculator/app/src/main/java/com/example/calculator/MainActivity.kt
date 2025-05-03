package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val keys = arrayListOf(
        "AC", "%", "<", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "00", "0", ".", "=",
    )


    private lateinit var binding: ActivityMainBinding


    fun show(x: String) {
        Toast.makeText(this@MainActivity, x, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setSupportActionBar(binding.myToolbar)

        binding.result.text="0.0"

        binding.RView.adapter = KeysAdapter().apply {
            submitList(keys)
            updateTextView = { it: String ->
                this@MainActivity.binding.result.text = it
            }
            showToast = ::show
        }
    }
}