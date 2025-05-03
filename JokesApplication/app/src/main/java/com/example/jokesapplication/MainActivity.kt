package com.example.jokesapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private fun fetchData(x: String) {
        val retrofitBuild = Retrofit.Builder()
            .baseUrl("https://official-joke-api.appspot.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuild.getProject(x)
        retrofitData.enqueue(object : Callback<List<Jokes>?> {
            override fun onResponse(call: Call<List<Jokes>?>, response: Response<List<Jokes>?>) {
                val data: List<Jokes> = response.body()!!
                val Rview = findViewById<RecyclerView>(R.id.RView)


                Log.d("Setting", data.toString())
                Rview.adapter = MyAdapter(this@MainActivity, data)
                Rview.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<List<Jokes>?>, t: Throwable) {

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData("10")

        findViewById<Button>(R.id.button).setOnClickListener {
            var getNum = findViewById<EditText>(R.id.editTextText).text.toString()
            if (getNum.toInt() <= 451) {
                fetchData(getNum)

            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Enter a number less than 451",
                    Toast.LENGTH_SHORT
                ).show()
            }
//            451

        }

    }
}