package com.example.callmethodapihit.ui.mainActivity

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callmethodapihit.di.RetrofitInstance
import com.example.callmethodapihit.data.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    var title = ObservableField<String>("Get Title")
    var price = ObservableField<String>("Get Price")


    fun apiRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<ResponseData> = RetrofitInstance.apiInterface.getData()

            if (response.isSuccessful) {
                val body:
                        ResponseData? = response.body()
                if (body != null) {
                    title.set(body.title)
                    price.set(body.price.toString())
                }
            }
        }
//
//        fun setMultipleData(): ArrayList<ResponseData> {
//            var x = viewModelScope.launch(Dispatchers.IO) {
//                val response: Response<ArrayList<ResponseData>> =
//                    RetrofitInstance.apiInterfaceMultipleData.getData()
//                if (response.isSuccessful) {
//                    var body = response.body()
//                    RviewData.set(body)
//                }
//            }
//        }

//        object : Callback<ResponseData>{
//            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
//
//            }
//
//            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        }
    }

}


