package com.example.flowandroid.main2

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun flowWithErrorHandling(): Flow<Int> = flow {
    for (i in 1..10) {
        emit(i)
        Log.d("sendingdata",i.toString())
        kotlinx.coroutines.delay(100)
    }
}.catch {
    emit(0)
}


class ViewModelMain2 : ViewModel() {
    var ldx = ObservableField<Int>()

    fun collectPackage() {
        viewModelScope.launch {
            flowWithErrorHandling().collect { message:Int ->
                ldx.set(message)
                Log.d("updating",message.toString())
            }
        }
    }


}