package com.example.workmanagerandservice.workmanagerandservice

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerEg(private var appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        Toast.makeText(applicationContext," ${inputData.getString("num")} working working in workerEG",Toast.LENGTH_LONG).show()
        Log.d("workingOnWorker", " ${inputData.getString("num")} working working in workerEG")
        return Result.success()
    }
}