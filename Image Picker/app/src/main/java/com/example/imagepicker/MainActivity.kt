package com.example.imagepicker

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagepicker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var adapter = ImagesAdapter(this)
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uri ->
            val currentList = adapter.currentList.toMutableSet()
            for (i in uri.indices) {
                currentList.add(ImagesPicked(uri[i], uri[i].lastPathSegment.toString()))
            }
            adapter.submitList(currentList.toList())
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.RView.setLayoutManager(StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL))
        binding.RView.adapter = adapter

        binding.btn.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }



    }


}