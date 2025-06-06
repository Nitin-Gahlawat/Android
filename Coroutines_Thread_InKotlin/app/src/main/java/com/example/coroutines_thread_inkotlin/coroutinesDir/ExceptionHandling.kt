package com.example.coroutines_thread_inkotlin.coroutinesDir

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun userData(): String {
    delay(2000)
    return  "User Data"
}

suspend fun productList(): String {
    delay(3000)
    return "Product Data"
}
fun main() {

    runBlocking {
        val job=launch {
            println("Hello world")
            delay(10000)
            println("This won't be printed if the job is cancelled.")

        }
        delay(1000)
        job.cancel(CancellationException("Error iin reading and writing"))
        job.join()


    }

}