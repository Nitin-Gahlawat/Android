package com.example.bitmapcompression


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bitmapcompression.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException





class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var bitmap: Bitmap? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { contentUri ->

            if (contentUri != null) {
                bitmap = convertJpegToBitmap(contentUri)
                binding.image.setImageBitmap(bitmap)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        binding.image.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }




        binding.compressbtn.setOnClickListener {

            val maxSizeString = binding.maxSizeBtn.text.toString()

            var maxSize = 50.0
            if (maxSizeString != "") {
                maxSize = maxSizeString.toDouble()
            }


            val outputPathname: String = cacheDir.toString()
            val outfile = File(outputPathname, "hello world.jpeg")

            if (!outfile.exists()) {
                outfile.createNewFile()
            }



            if (bitmap != null) {
                compressBitmap(
                    bitmap!!, outfile, maxSize.toInt()
                )
                Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "the bitmap is null", Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun compressBitmap(bitmap: Bitmap, targetFile: File, maxSizeInKB: Int) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        var quality = 100
        val compressedBitmap: Bitmap = bitmap

        do {
            byteArrayOutputStream.reset()

            compressedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)

            val compressedData = byteArrayOutputStream.toByteArray()
            val currentSizeInKB = compressedData.size / 1024
            if (currentSizeInKB <= maxSizeInKB) {
                try {
                    val fileOutputStream = FileOutputStream(targetFile)
                    fileOutputStream.write(compressedData)
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                break
            }

            quality -= 10

        } while (quality > 0)
        if (quality == 0) {
            println("Unable to reduce size within limits. Consider resizing the image.")
        }
    }


    private fun convertJpegToBitmap(contentUri: Uri): Bitmap? {
        return try {
            val inputStream = this.contentResolver.openInputStream(contentUri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}