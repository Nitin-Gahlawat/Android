package com.example.apibasics

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var productList: List<Product>
    private val baseUrl = "https://dummyjson.com/"


    private fun fetchDataAndDisplay() {
        val retrofitBuild = Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuild.getProject()

        retrofitData.enqueue(object : Callback<MyDataProducts?> {
            override fun onResponse(
                call: Call<MyDataProducts?>,
                response: Response<MyDataProducts?>
            ) {
                val data: MyDataProducts? = response.body()
                productList = data?.products!!
                val x: RecyclerView = findViewById(R.id.RView)
                x.layoutManager = LinearLayoutManager(this@MainActivity)
                x.adapter = MyAdapter(this@MainActivity, productList)
            }

            override fun onFailure(call: Call<MyDataProducts?>, t: Throwable) {
                Log.d("log2", "hello world")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDataAndDisplay()
    }
}