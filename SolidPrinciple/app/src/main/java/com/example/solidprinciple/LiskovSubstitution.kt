package com.example.solidprinciple

//Liskov Substitution Principle
//It says that if you have a program that works with a certain type of object, you should be able to use any subtype of that object without any problems.

//Meaning we should Not Reduce the properties which are present in the parent class


open class Laptop{
    open fun getScreen():Int{
        return 14
    }
}

class Dell: Laptop() {
    override fun getScreen():Int{
        return this.screen()
    }
    private fun screen(): Int {
        return 14
    }
}

class Lenovo: Laptop() {
    override fun getScreen(): Int {
        return 15
    }
}

fun main() {
    var x=Dell()
    var y=Lenovo()
//    Both get screen Method will work because they are coming from the Laptop interface
    println(" ${x.getScreen()} ${y.getScreen()}")
}