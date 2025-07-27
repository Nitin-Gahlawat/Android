package com.example.callmethodapihit.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products/1")
    suspend fun getData(): Response<ResponseData>
}


interface GetMultipleData {
    @GET("products?limit=20")
    suspend fun getData(): Response<ArrayList<ResponseData>>

}