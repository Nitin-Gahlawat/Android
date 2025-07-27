package com.example.audiofetch

import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.audiofetch.databinding.ActivityMainBinding
import com.ketch.Ketch
import com.ketch.NotificationConfig
import com.ketch.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var songsList = arrayListOf(
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"
    )


    private fun getKetch(): Ketch {
        val ketch = Ketch.builder().setNotificationConfig(
            config = NotificationConfig(
                enabled = true,
                channelName = "channelName",
                channelDescription = "channelDescription",
                importance = NotificationManager.IMPORTANCE_HIGH,
                smallIcon = android.R.drawable.star_on,
                showSpeed = true,
                showSize = true,
                showTime = true
            )
        ).build(this@MainActivity)
        return ketch
    }

    fun donwnload_Function(downloadUrl: String, position: Int): File? {
        val destination: File = filesDir
        val filenamearray = downloadUrl.split("/")
        val filename = filenamearray[filenamearray.lastIndex]

        val file = destination.listFiles()?.firstOrNull { it.name == filename }
        try {
            if (file != null) {
                val fileUri: File = file
                return fileUri
            } else {
                val ketch = getKetch()
                val id = ketch.download(downloadUrl, destination.toString(), filename)

                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        ketch.observeDownloadById(id).flowOn(Dispatchers.IO)
                            .collect { downloadModel ->
                                Log.d("downloadingData", downloadModel.toString())
                                if (downloadModel.status == Status.SUCCESS)
                                    adapter.notifyItemChanged(position)
                                else if (downloadModel.status == Status.FAILED)
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Download Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                            }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("excception", "this is catch exception")
        } finally {
        }
        return destination.toPath().resolve(filename).toFile()
    }


    lateinit var adapter: SongsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        adapter = SongsAdapter(filesDir, songsList).apply {
            download = ::donwnload_Function
        }

        binding.RView.adapter = adapter


    }
}