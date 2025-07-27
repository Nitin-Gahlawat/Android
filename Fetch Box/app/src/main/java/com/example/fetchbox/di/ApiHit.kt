package com.example.fetchbox.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


//https://dummyjson.com/carts

@Module
@InstallIn(SingletonComponent::class)
object ApiHit {


    @Provides
    @Singleton
    fun getInstance(@ApplicationContext context :Context): Retrofit {
        val baseUrl="https://dummyjson.com/"
        return Retrofit.Builder().baseUrl(baseUrl).client(
            OkHttpClient().newBuilder()
                .addInterceptor(ChuckerInterceptor(context))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L, TimeUnit.MILLISECONDS).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}