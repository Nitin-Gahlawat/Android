package com.example.daggerhiltdemo.di.Toast


import android.content.Context
import android.widget.Toast
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext


class ToastManagerDagger @AssistedInject constructor(
    @ApplicationContext private val context: Context
) {
    fun sendToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}

@AssistedFactory
interface ToastManagerFactory {
    fun create(): ToastManagerDagger
}