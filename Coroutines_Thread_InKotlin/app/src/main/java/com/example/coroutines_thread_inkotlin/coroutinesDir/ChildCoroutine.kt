package com.example.coroutines_thread_inkotlin.coroutinesDir

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()= runBlocking {
    val job1=launch {
        val job2=launch(Dispatchers.IO + CoroutineName("coroutines1")) {
            println("Thread coroutines 1 ${Thread.currentThread().name}")
            println("child coroutines 1 ${coroutineContext[CoroutineName]}")
            delay(1000)
        }
        val job3=launch(Dispatchers.IO + CoroutineName("coroutines2")){
            println("Thread coroutines 2 ${Thread.currentThread().name}")
            println("child coroutines 2 ${coroutineContext[CoroutineName]}")
            delay(10)
        }
        job3.join()
        job2.join()
    }
    job1.start()

    val job2=launch {
        val job2=launch(Dispatchers.IO + CoroutineName("coroutines1")) {
            println("Thread coroutines 1 ${Thread.currentThread().name}")
            println("child coroutines 1 ${coroutineContext[CoroutineName]}")
            delay(1000)
        }
        val job3=launch(Dispatchers.IO + CoroutineName("coroutines2")){
            println("Thread coroutines 2 ${Thread.currentThread().name}")
            println("child coroutines 2 ${coroutineContext[CoroutineName]}")
            delay(10)
        }
        job3.join()
        job2.join()
    }
    job2.start()
    job1.join()
}