package com.example.photoframe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photoframe.databinding.ActivityMainBinding
import kotlin.math.abs

data class ImageData(val name: String, val address: Int)


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var imagedataarray = arrayListOf(
        ImageData("java", R.drawable.img),
        ImageData("python", R.drawable.img1),
        ImageData("js", R.drawable.img2),
        ImageData("kotlin", R.drawable.img3)
    )

    private fun displayData(position: Int) {
        val ele = imagedataarray[position]
        binding.image.setImageResource(ele.address)
        binding.nameElement.text = ele.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var ct: Int = 0
        displayData(ct)
        binding.prev.setOnClickListener {
            if (ct == 0) ct = 4 else ct -= 1
            displayData(abs(abs(ct) % 4))
        }
        binding.next.setOnClickListener {
            ct += 1
            displayData(abs(ct) % 4)
        }
    }
}