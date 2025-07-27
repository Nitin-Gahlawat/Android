package com.example.fetchbox.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchbox.data.Product
import com.example.fetchbox.data.ResponseData
import com.example.fetchbox.data.RoomCartDbDao
import com.example.fetchbox.data.RoomClassCartdb
import com.example.fetchbox.di.GetCartData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(private var dao: RoomCartDbDao, private var apiInstance: Retrofit) : ViewModel() {

    var adapter = DataAdapter()

    init {
        viewModelScope.launch {
            var i=0
            dao.getUser().collectLatest { list ->
                val currentList = adapter.currentList.toMutableList()
                currentList.addAll(list.map {
                    Product(
                        thumbnail = it.Img,
                        title = it.title + "$i"
                    )
                })
                adapter.submitList(currentList)
                i++
            }
        }
    }

    fun fetchDataFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val p: GetCartData = apiInstance.create(GetCartData::class.java)
            val result: Response<ResponseData> = p.getData()

            if (result.isSuccessful) {
                val tempList: ArrayList<Product> = arrayListOf()
                result.body()?.carts?.forEach { cart ->
                    tempList.addAll(cart.products)
                }
                saveDataToDb(tempList)
            } else {
                Log.d("prodata", "not sucess")
            }
        }
    }


    private fun saveDataToDb(data: ArrayList<Product>) {
        viewModelScope.launch(Dispatchers.IO) {

            data.forEach {
                dao.insertCartDb(
                    RoomClassCartdb(
                        id = 0,
                        title = it.title.toString(),
                        Img = it.thumbnail.toString()
                    )
                )
            }
        }
    }

    fun deleteData() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllCart()
        }
        adapter.submitList(arrayListOf())

    }
}

//    fun fetchFromDb() {
//        val db = Room.databaseBuilder(
//            context = context,
//            AppDatabase::class.java, "MyCart"
//        ).build()
//        val cart = db.cartDao()
//        viewModelScope.launch(Dispatchers.IO) {
//            val tempList: ArrayList<Product> = arrayListOf()
//
//            cart.getUser()forEach {
//                withContext(Dispatchers.Main) {
//                    Log.d("getting",it.title)
//                    tempList.add(Product(it.title, it.Img))
//                }
//            }
//            _productList.emit(tempList)
//        }
//    }

