package com.example.dependencyinjecton

import javax.inject.Inject

class Ram {
        fun getRam(): String {
            return "1024gb"
        }
}

class Computer @Inject constructor(var r:Ram){
    fun getComputer(): String {
        r.getRam()
        return "call of computer"
    }
}
