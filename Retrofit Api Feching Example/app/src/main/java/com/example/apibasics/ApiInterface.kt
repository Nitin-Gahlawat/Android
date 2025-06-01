package com.example.apibasics
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {


    @GET("products")
    fun getProject(): Call<MyDataProducts>
}