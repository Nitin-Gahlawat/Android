package com.example.solidprinciple

//Open/Closed Principle
//The Open/Closed Principle is a rule in object-oriented design that says classes, modules, functions, and other software entities should be open for extension but closed for modification.


//Bad Code
// For Every New Insurance We have to create a New Function In the InsuranceCompany class
//class HomeInsurance{
//    fun isLoyalCustomer(): Boolean {
//        return true
//    }
//}
//
//class LifeInsurance{
//    fun isLoyalCustomer(): Boolean {
//        return true
//    }
//}
//
//class CarInsurance{
//    fun isLoyalCustomer(): Boolean {
//        return true
//    }
//}
//
//
//class InsuranceCompany{
//    fun discountRate(HI:HomeInsurance):Int{
//        return if(HI.isLoyalCustomer()) 10 else 7
//    }
//
//    fun discountRate(LI:LifeInsurance):Int{
//        return if(LI.isLoyalCustomer()) 30 else 78
//    }
//    fun discountRate(CI:CarInsurance):Int{
//        return if(CI.isLoyalCustomer()) 10 else 7
//    }
//}


//Good Code
interface Customer{
    fun isLoyalCustomer():Boolean
}


class HomeInsurance():Customer{
    override fun isLoyalCustomer(): Boolean {
        return true
    }
}


class LifeInsurance():Customer{
    override fun isLoyalCustomer(): Boolean {
        return true
    }

}


class InsuranceCompany() {
    fun getDiscountedRate(inc: Customer): Int {
        return if (inc.isLoyalCustomer()) 20 else 30
    }
}
fun main() {
    var ir=InsuranceCompany()
    println(ir.getDiscountedRate(HomeInsurance()))
    println(ir.getDiscountedRate(LifeInsurance()))
}