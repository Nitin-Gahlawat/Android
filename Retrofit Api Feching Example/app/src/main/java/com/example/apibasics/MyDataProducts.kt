package com.example.apibasics

data class MyDataProducts(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)