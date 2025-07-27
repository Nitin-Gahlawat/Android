package com.example.workmanagerandservice

import android.app.Application

class App: Application() {
    companion object{
        var flows=ServiceDataImpl()
    }
}