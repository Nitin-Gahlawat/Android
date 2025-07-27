package com.example.dependencyinjecton

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

class Engine() {
    companion object {
        fun getPetrolEngine() {
            Log.d("ENGCall", "Calling from Engin")
        }
    }
}

@Singleton
class Car @Inject constructor() {
    fun getCar(): String {
        Engine.getPetrolEngine()
        return "Getting Car"
    }
}