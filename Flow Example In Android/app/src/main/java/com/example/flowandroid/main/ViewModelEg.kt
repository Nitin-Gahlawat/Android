package com.example.flowandroid.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun flowWithErrorHandling(): Flow<Int> = flow {
    for (i in 1..15) {
        emit(i)
        kotlinx.coroutines.delay(100)
    }
}.catch { e ->
    emit(0)
}


class ViewModelEg : ViewModel() {
    var ldx: LiveData<ArrayList<Int>> = MutableLiveData(arrayListOf())

    fun collectPackage(setAdapterData: (ArrayList<Int>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            flowWithErrorHandling().collect { message ->
                ldx.value?.add(message)
                withContext(Dispatchers.Main) {
                    Log.d("running ", "this is the ViewModelEd ")
                    ldx.value?.let { setAdapterData(it) }
                }
            }
        }

    }


}