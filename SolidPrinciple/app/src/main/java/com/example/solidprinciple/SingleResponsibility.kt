package com.example.solidprinciple

//S -Single Responsibility principle
//Every class should have a Specific Function(or event),It means that a class should possess only one responsibility or a job
//Bad Code
//this class Contain many responsibilities
//class EmailAndPasswordValidator{
//    fun ValidateEmail(){
//        println("validating email")
//    }
//    fun ValidatePassword(){
//        println("Validating Password")
//    }
//}




//Good Code
class EmailValidator(){
     fun validate(toValidate: String) {
        println("validating $toValidate")
    }
}


class PasswordValidator(){
     fun validate(toValidate: String) {
        println("validating $toValidate")
    }

}

fun main() {
    val x=EmailValidator()
    x.validate("abc@gmail.com")

    val y=PasswordValidator()
    y.validate("123456789")
}