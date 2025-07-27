package com.example.dependencyinjecton

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

// Dependency injection dagger hilt
//  Manual dependency and third party libraries like dagger hilt
//  and example of hilt
// Field injection , constructor injection @provide in module
@HiltAndroidApp
class TopApplication : Application() {

    @Inject
    lateinit var c: Car

    override fun onCreate() {
        super.onCreate()
        c.getCar()
    }
}