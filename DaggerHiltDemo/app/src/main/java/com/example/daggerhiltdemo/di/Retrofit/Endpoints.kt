package com.example.daggerhiltdemo.di.Retrofit

import com.example.daggerhiltdemo.di.RoomDatabase.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.daggerhiltdemo.dataClass.SpaceData

interface Endpoints {
    @GET("users/{user}")
    suspend fun getUser(@Path("user") username: String): User

    @POST("api/spacecrafts") //
    suspend fun getSpaceData(): SpaceData
}