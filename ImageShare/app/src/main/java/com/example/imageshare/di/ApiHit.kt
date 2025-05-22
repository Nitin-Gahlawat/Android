package com.example.imageshare.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiHit {
    @Provides
    @Singleton
    fun getInstance(): Retrofit {
        val baseUrl = "https:BaseUrl.com/"

        class NewInterceptor : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "token")
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return Retrofit.Builder().baseUrl(baseUrl).client(
            OkHttpClient().newBuilder().addInterceptor(NewInterceptor())
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .connectTimeout(100000L, TimeUnit.MILLISECONDS)
                .readTimeout(100000L, TimeUnit.MILLISECONDS)
                .writeTimeout(100000L, TimeUnit.MILLISECONDS).build()
        )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


}