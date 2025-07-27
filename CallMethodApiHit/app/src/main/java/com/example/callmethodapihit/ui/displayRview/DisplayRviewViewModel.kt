package com.example.callmethodapihit.ui.displayRview

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callmethodapihit.data.ResponseData
import com.example.callmethodapihit.data.getData
import com.example.callmethodapihit.di.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayRviewViewModel : ViewModel() {

    var adapter = RecyclerViewAdapter()


    fun setMultipleData(
        context: Context
    ) {
        var x = viewModelScope.launch(Dispatchers.IO) {
            val result = getData(context = context, condition = {
                RetrofitInstance.apiInterfaceMultipleData.getData()
            },
                success = { body ->
                    val response = body as List<ResponseData>
                    viewModelScope.launch(Dispatchers.Main) {
                        adapter.submitList(response)
                    }
                },
                error = { s: String ->
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

}