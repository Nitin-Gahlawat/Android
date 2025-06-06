package com.example.coroutines_thread_inkotlin
import java.lang.Thread


class ThreadInKotlin: Thread() {
    override fun run() {
        super.run()
        print("In Thread 1")
        for(i in 1..100){
            println("Thread1 $i")
            Thread.sleep(100)
        }
    }
}

fun main() {


////    MultiThreading using Thread Class
    var thread1=ThreadInKotlin()
    thread1.start()

//    MultiThreading using Runnable Interface
    Thread(object : Runnable {
        override fun run() {
            print("In Thread 2")
            for(i in 1..100){
                println("Thread2 $i")
                Thread.sleep(100)
            }
        }
    }).start()


    println("Main Thread In Kotlin")
    for(i in 1..100){
        println("Main Thread $i")
        Thread.sleep(1000)
    }


}