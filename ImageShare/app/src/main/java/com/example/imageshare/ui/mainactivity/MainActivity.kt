package com.example.imageshare.ui.mainactivity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.imageshare.R
import com.example.imageshare.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    Note:-Add a Baseurl and end point in di(AiHit,SendingData)

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelMain by viewModels()
    private var imageUri: Uri? = null
    private lateinit var imageView: ImageView

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                imageView.setImageURI(uri)
                imageUri = uri
                viewModel.sendDataToServer(imageUri!!, this)
            } else {
                Toast.makeText(this@MainActivity, "Image Pick failed", Toast.LENGTH_SHORT).show()
            }
        }

    private fun getIMageFromGallary() {
        lifecycleScope.launch(Dispatchers.IO) {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { result: Boolean ->
            if (result) {
                imageView.setImageURI(imageUri)
            } else {
                Toast.makeText(this@MainActivity, "Image capture failed", Toast.LENGTH_SHORT).show()
            }
        }


    private fun getImageFromCamara() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), 1001
            )
        } else {


            val imageFile = File(externalCacheDir, "captured_image.jpg")
            imageUri = FileProvider.getUriForFile(
                baseContext, baseContext.applicationContext.packageName + ".provider", imageFile
            )


            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.putExtra(
                MediaStore.EXTRA_OUTPUT, imageUri
            )
            takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            takePictureLauncher.launch(imageUri!!)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        imageView = binding.ProfileImage

        val bottomSheet = GetImageFragment()
        bottomSheet.getImageFromCamara = ::getImageFromCamara
        bottomSheet.getIMageFromGallary = ::getIMageFromGallary

        binding.UploadImg.setOnClickListener {


            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }
}
