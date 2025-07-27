package com.example.solidprinciple

//Dependency Inversion
//The Dependency Inversion Principle is a SOLID principle that states that high-level modules should not depend on low-level modules, but both should depend on abstractions

//Abstraction Between High Level Module And Low Level Module
interface MobileDeveloper{
    fun developMobileApp()
}


//Low Level Module
class AndroidDeveloper():MobileDeveloper{
    override fun developMobileApp(){
        println("Android Developer")
    }
}

//Low Level Module
class IosDeveloper():MobileDeveloper{
    override fun developMobileApp(){
        println("IOS Developer")
    }
}


fun printDeveloper(x:MobileDeveloper){
    x.developMobileApp()
}


//High Level Module
fun main() {
//    Taking Interface type for Abstraction
    var x:MobileDeveloper=AndroidDeveloper()
    printDeveloper(x)
    x=IosDeveloper()
    printDeveloper(x)
}