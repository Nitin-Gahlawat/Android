package com.example.callmethodapihit.di

import com.example.callmethodapihit.data.ApiInterface
import com.example.callmethodapihit.data.GetMultipleData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//https://jsonplaceholder.typicode.com


object RetrofitInstance{
    private var apiUrl="https://fakestoreapi.com/"
    private val retrofit by lazy {
            Retrofit.Builder().baseUrl(apiUrl)
                .client(
                    OkHttpClient().newBuilder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000L, TimeUnit.MILLISECONDS).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

    val apiInterfaceMultipleData: GetMultipleData by lazy {
        retrofit.create(GetMultipleData::class.java)
    }
}