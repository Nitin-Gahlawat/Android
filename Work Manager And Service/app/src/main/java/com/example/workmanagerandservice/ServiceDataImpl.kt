package com.example.workmanagerandservice

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class ServiceDataImpl {
    private var shared = MutableSharedFlow<String>()
    private var state = MutableStateFlow("The State flow is -1")

    suspend fun setSharedFlow(value: String) {
        shared.emit(value = value)
    }

    fun getSharedFlow(): SharedFlow<String> {
        return shared.asSharedFlow()
    }

    suspend fun setStateFlow(value: String) {
        state.emit(value = value)
    }
    fun getStateFlow(): StateFlow<String> {
        return state.asStateFlow()
    }


}