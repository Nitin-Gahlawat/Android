package com.example.paggination.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.paggination.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: MyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MyAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest { pagingData: PagingData<MyDataItem> ->
                adapter.submitData(pagingData)
            }
        }

    }
}