package com.example.serviceandbroadcast.services

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ServiceHandlerInterface {

    fun getValue() : StateFlow<String>

    suspend fun setValue(value : String)
}