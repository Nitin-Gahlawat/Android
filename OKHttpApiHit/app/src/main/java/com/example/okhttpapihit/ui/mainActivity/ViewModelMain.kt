package com.example.okhttpapihit.ui.mainActivity

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.okhttpapihit.data.ApiDataClass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


fun fetchData(): ApiDataClass? {
    val client = OkHttpClient()
    val request: Request = Request.Builder().url("https://dummyjson.com/products/2").build()

    var r: Response? = null
    try {
        r = client.newCall(request).execute()
        return parsePosts(r.body?.string())

    } catch (e: IOException) {
        if (r != null) {
            throw IOException("Unexpected response: " + r.code)
        }
    }
    return null
}

private fun parsePosts(json: String?): ApiDataClass {
    val listType = object : TypeToken<ApiDataClass>() {}.type
    return Gson().fromJson(json, listType)
}


class ViewModelMain : ViewModel() {


    var title = ObservableField("Title")
    var description = ObservableField("Description")
    var category = ObservableField("Category")
    var price = ObservableField("Price")


    fun gettingData(setImage: suspend (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val x: ApiDataClass? = fetchData()
                title.set(x?.title)
                description.set(x?.description)
                price.set(x?.price.toString())
                category.set(x?.category)
                withContext(Dispatchers.Main) {
                    setImage(x?.thumbnail)
                }

                Log.d("getting", x?.id.toString())
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}