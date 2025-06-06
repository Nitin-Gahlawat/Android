package com.example.coroutines_thread_inkotlin




import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//import kotlin.concurrent.thread

//fun main() {
//    println("main thread ${Thread.currentThread().name}")
//    Thread ({
//        println("THE thread ${Thread.currentThread().name}")
//        Thread.sleep(1000)
//        println("main thread ${Thread.currentThread().name}")
//    }).start()
//
//    println("main thread ${Thread.currentThread().name}")
//}


//fun main() =
//    runBlocking {
//        println("main thread ${Thread.currentThread().name}")
//        GlobalScope.launch {
//            println("The thread name is  ${Thread.currentThread().name}")
//            delay(2000L)
//            println("The thread name is  ${Thread.currentThread().name}")
//        }
//        println("main thread ${Thread.currentThread().name}")
//
//        delay(3000)
//    }

fun main() {
    runBlocking {
        println("main thread ${Thread.currentThread().name}")
        var j: Deferred<Int> =async {
            println("The thread name is  ${Thread.currentThread().name}")
            delay(2000L)
            println("The thread name is  ${Thread.currentThread().name}")
            return@async 5
        }
        var res=j.await()+250
        println(res)

        println("main thread ${Thread.currentThread().name}")
    }


}