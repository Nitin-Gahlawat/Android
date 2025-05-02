package com.example.imagepicker

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.imagepicker.databinding.ActivityDisplayBigImagesBinding

class DisplayBigImages : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBigImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayBigImagesBinding.inflate(layoutInflater)

        setContentView(binding.root)
        var i=intent
        var x:Uri= Uri.parse(i.getStringExtra("ImageUri"))
        binding.bigimage.apply {

            setImageURI(x)

        }


        binding.text.setText(x.lastPathSegment)
        Log.d("newtext",binding.text.text.toString())

    }
}