package com.example.okhttpapihit.ui.mainActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.okhttpapihit.R
import com.example.okhttpapihit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    suspend fun setImage(imageUrl: String?) {
        Glide
            .with(this)
            .load(imageUrl)
            .centerCrop()
            .placeholder(android.R.drawable.arrow_down_float)
            .into(binding.newImage)


    }


    private lateinit var binding: ActivityMainBinding

    private val viewModel: ViewModelMain? by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel


        binding.btn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Getting Data", Toast.LENGTH_LONG).show()
            viewModel?.gettingData(::setImage)
        }


    }
}