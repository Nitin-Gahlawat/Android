package com.example.coroutines_thread_inkotlin.coroutinesDir

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        var job1 = launch(Dispatchers.IO) {
            println("Working on IO Dispatchers")
        }

        job1.start()


        var job3 = CoroutineScope(Dispatchers.Default).launch {
            println("Working on Default Dispatchers")
        }

        job3.start()

        var job4 = CoroutineScope(Dispatchers.Unconfined).launch {
            println("Working on Unconfined Dispatchers")
        }
        job4.start()


        CoroutineScope(Dispatchers.Main).launch {
            println("Running on the main thread")
        }

//        var job2 = CoroutineScope(Dispatchers.Main).launch {
//            println("Working on Main Dispatchers")
//        }
//        job2.start()

//        var job5 = CoroutineScope(Dispatchers.Main.immediate).launch {
//            println("Working on Main.immediate Dispatchers")
//        }
//
//        job5.start()
        launch {
            println("started")
            delay(1000)
            println("ended")

        }


    }

}

