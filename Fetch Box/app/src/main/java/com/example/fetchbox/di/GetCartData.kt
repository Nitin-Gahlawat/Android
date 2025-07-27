package com.example.fetchbox.di

import com.example.fetchbox.data.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface GetCartData {
    @GET("/carts?limit=2")
    suspend fun getData():Response<ResponseData>
}