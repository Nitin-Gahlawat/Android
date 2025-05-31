package com.example.serviceandbroadcast.services

import android.app.Application

class App : Application() {

    companion object{
        val serviceHandlerInterface: ServiceHandlerInterface by lazy {
            ServiceHandlerImpl()
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}