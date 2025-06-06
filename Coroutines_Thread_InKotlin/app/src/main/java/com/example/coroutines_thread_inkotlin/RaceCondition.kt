package com.example.coroutines_thread_inkotlin

fun main(){
    var i:Int=4

    Thread{
            var m = i + 1;

            println(Thread.currentThread().name)
            Thread.sleep(100)
            i = m
    }.start()

    Thread{
            var m = i - 1;
        println(Thread.currentThread().name)
            Thread.sleep(100)
            i = m
    }.apply {
        start()
    }
    Thread.sleep(1000)
    print(i)

}