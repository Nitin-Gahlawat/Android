package com.example.solidprinciple

//Interface Segregation
//It says that when we make different parts of a program, we shouldn’t make them all the same way. Instead, we should make them smaller and more specific, so that other parts of the program don’t have to depend on things they don’t need.


interface Fax {
    fun sendFax()
    fun getFax()
}

interface Print {
    fun makePrint()
}

interface Scanner {
    fun makeScanner()
}

class Cannon : Print, Scanner {


    override fun makeScanner() {
        println("Getting the Fax")
    }

    override fun makePrint() {
        println("Getting a Print out")
    }


}

fun main() {
    var i: Cannon = Cannon()
    i.makeScanner()
    i.makePrint()
}