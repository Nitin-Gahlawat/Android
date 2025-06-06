package com.example.coroutines_thread_inkotlin.coroutinesDir

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


fun main():Unit=runBlocking{

    val result: Deferred<String> = async {
        delay(50)
        return@async "World"
    }
    val x="Hello "+result.await()
    println(x)


    val job=launch {
        delay(1500)
        println("Calling from the launch")
        withContext(Dispatchers.IO) {
            println("performing Io operations ")
            delay(50)
        }
    }
    job.start()

}
