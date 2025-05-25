package com.example.roomdatabaseandroid

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//Setting Data to the Room
class MainActivity : AppCompatActivity() {

    private val dataBase by lazy {
        MainClass.database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        Inserting data
        findViewById<Button>(R.id.save).setOnClickListener {
            var job = lifecycleScope.launch(Dispatchers.IO) {
                var x = findViewById<AppCompatEditText>(R.id.toSaveName).text.toString()
                var y = findViewById<AppCompatEditText>(R.id.toSavePhone).text.toString()
                val rowId: Long =
                    dataBase.contactDao().insertContact(Contact(0, x, y))
                if (rowId != -1L) {
                    Log.d("isSuccess", "Data inserted successfully ")
                } else {
                    // Insert failed
                    Log.d("isSuccess", "Data not successfully 1")
                }
            }
        }

        findViewById<Button>(R.id.get).setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                var x: List<Contact> = dataBase.contactDao().selectContact()
                var a: ArrayList<String> = arrayListOf()

                for (i in x) {
                    a.add("${i.name} ${i.id} ${i.phone}")
                }

                withContext(Dispatchers.Main) {
                    val adapter: ArrayAdapter<String> =
                        ArrayAdapter<String>(
                            this@MainActivity,
                            android.R.layout.simple_list_item_1,
                            a
                        )
                    findViewById<ListView>(R.id.RView).adapter = adapter
                }
            }
        }
    }
}