package com.example.callmethodapihit.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.callmethodapihit.R
import com.example.callmethodapihit.ui.displayRview.DisplayRview
import com.example.callmethodapihit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel? by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel

        binding.btn.setOnClickListener {
            viewModel?.apiRequest()
        }

        binding.newMove.setOnClickListener{
            startActivity(Intent(this, DisplayRview::class.java))
        }
    }
}





