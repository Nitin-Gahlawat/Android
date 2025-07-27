package com.example.workmanagerandservice.workmanagerandservice

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanagerandservice.R

//Create a readme for my project showcasing the use of both service and workManager in markdown format
class WorkingWorker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_working_worker)


//        Periodic Request
//        val work:WorkRequest= PeriodicWorkRequestBuilder<WorkerEg>(1,TimeUnit.HOURS).build()

//        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<WorkerEg>().setConstraints(
//            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
//        ).build()
//
//        findViewById<MaterialButton>(R.id.button).setOnClickListener {
//
//            WorkManager.getInstance(this).enqueue(uploadWorkRequest)
//        }


        val work1: OneTimeWorkRequest = OneTimeWorkRequestBuilder<WorkerEg>().apply {
            setInputData(Data.Builder().putString("num", "Worker1").build())
        }.build()
        val work2: OneTimeWorkRequest = OneTimeWorkRequestBuilder<WorkerEg>().apply {
            setInputData(Data.Builder().putString("num", "Worker2").build())
        }.build()
        val work3: OneTimeWorkRequest = OneTimeWorkRequestBuilder<WorkerEg>().apply {
            setInputData(Data.Builder().putString("num", "Worker3").build())
        }.build()


        findViewById<Button>(R.id.Button).setOnClickListener{
            val workManager = WorkManager.getInstance(this)
            workManager.beginWith(listOf(work1, work2)).then(work3).enqueue()
        }

    }
}