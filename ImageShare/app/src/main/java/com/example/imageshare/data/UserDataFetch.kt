package com.example.imageshare.data

data class UserDataFetch(
    val data: Data,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)