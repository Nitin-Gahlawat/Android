package com.example.callmethodapihit.ui.displayRview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.callmethodapihit.R
import com.example.callmethodapihit.databinding.ActivityDisplayRviewBinding





class DisplayRview : AppCompatActivity() {

    lateinit var binding: ActivityDisplayRviewBinding

    private val viewModel: DisplayRviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_display_rview)
        binding.displayViewModel=viewModel

        viewModel.setMultipleData(this)
        binding.Rview.adapter  =viewModel.adapter
        binding.Rview.layoutManager=GridLayoutManager(this,2)


//        binding.DisplayViewModel = viewModel
    }
}