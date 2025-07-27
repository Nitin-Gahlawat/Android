package com.example.flowandroid.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flowandroid.R
import com.example.flowandroid.databinding.ActivityMainBinding
import com.example.flowandroid.main2.UsingLiveData

class MainActivity : AppCompatActivity() {


    fun setAdapterData(list: ArrayList<Int>) {
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val viewModel = ViewModelEg()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel


        adapter = RecyclerViewAdapter()
        adapter.submitList(viewModel.ldx.value)
        binding.Rview.adapter = adapter

        binding.showData.setOnClickListener {
            viewModel.collectPackage(::setAdapterData)
            binding.showData.visibility = View.GONE
            binding.showOtherExample.visibility = View.VISIBLE
        }

        binding.showOtherExample.setOnClickListener {
            startActivity(Intent(this, UsingLiveData::class.java))
        }


    }
}