package com.example.serviceandbroadcast.services

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class ServiceHandlerImpl : ServiceHandlerInterface {

    private val serviceState = MutableStateFlow("0")

    override fun getValue(): StateFlow<String> {
        return serviceState.asStateFlow()
    }

    override suspend fun setValue(value: String) {
        serviceState.emit(value = value)
    }
}