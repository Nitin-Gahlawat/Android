package com.example.jokesapplication

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ApiInterface {
    @GET("jokes/random/{noOfJokes}")
    fun getProject(@Path("noOfJokes") userId: String): Call<List<Jokes>>
}
