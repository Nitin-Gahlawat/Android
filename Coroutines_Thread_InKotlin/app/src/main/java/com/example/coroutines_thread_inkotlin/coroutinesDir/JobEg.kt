package com.example.coroutines_thread_inkotlin.coroutinesDir

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking



fun main(): Unit= runBlocking{
    val jobVar=launch {
        delay(1000)
      println("Thread name ${Thread.currentThread().name}")
    }


    jobVar.join()
}