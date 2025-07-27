package com.example.flowandroid.main2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flowandroid.R
import com.example.flowandroid.databinding.ActivityUsingLiveDataBinding

class UsingLiveData : AppCompatActivity() {
    lateinit var binding: ActivityUsingLiveDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_using_live_data)

        var viewModel = ViewModelMain2()
        binding.viewModel = viewModel
        binding.button.setOnClickListener {
            viewModel.collectPackage()

        }

    }
}