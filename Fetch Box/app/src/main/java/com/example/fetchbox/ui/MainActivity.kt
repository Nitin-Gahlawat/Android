package com.example.fetchbox.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fetchbox.R
import com.example.fetchbox.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[ViewModelMain::class.java]
        binding.viewModel = viewModel
        binding.Rview.adapter = viewModel.adapter

        binding.btn.apply {
            setOnClickListener {
                viewModel.fetchDataFromApi()
            }
        }
        binding.deleteBtn.setOnClickListener {
            viewModel.deleteData()
        }
    }
}
