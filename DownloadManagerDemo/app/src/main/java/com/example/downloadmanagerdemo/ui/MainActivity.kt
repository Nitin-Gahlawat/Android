package com.example.downloadmanagerdemo.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.downloadmanagerdemo.R
import com.example.downloadmanagerdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


//    private val URL_link = "https://www.pci.nic.in/pdf/Syllabus_B_Pharm.pdf"

//    private val URL_link1 =
//        "https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/800px-Java_programming_language_logo.svg.png"

    companion object {
        const val URL = "url"
        const val distination = "distination"
        const val filename = "filename"
    }


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        val builder = VmPolicy.Builder()

        StrictMode.setVmPolicy(builder.build())


        binding.DownloadBtn.setOnClickListener {
            val externalDownloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath
            val data = Data.Builder().put(URL, binding.urlLink.text.toString())
                .put(MainActivity.distination, externalDownloadsPath.toString())
                .build()


            val workRequest: WorkRequest =
                OneTimeWorkRequest.Builder(DownloadWorker::class.java).setInputData(data)
                    .build()
            WorkManager.getInstance(this).enqueue(
                workRequest

            )
        }
    }


}