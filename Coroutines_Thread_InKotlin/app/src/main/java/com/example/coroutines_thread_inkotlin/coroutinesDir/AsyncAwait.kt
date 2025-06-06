package com.example.coroutines_thread_inkotlin.coroutinesDir

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        var res1: Deferred<Int> = async {
            delay(1000)
            return@async 1
        }

        val res2:Deferred<Int>  = async {
            delay(10)
            return@async 2
        }
        var res3:Deferred<Int>  = async {
            delay(20)
            return@async 3
        }
        var res4:Deferred<Int>  = async {
            delay(5000)
            return@async 4
        }
        var finalRes:List<Int> = awaitAll(res1,res2,res3)

        print(finalRes.sum()+res4.await())

    }
}