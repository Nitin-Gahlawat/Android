package com.example.listviewproject2

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
data class User(
    val name: String, val body: String,
    val yearOfRelease: String, val imgId: Int
)
class MainActivity : AppCompatActivity() {
    var programmingDisplayData:ArrayList<User> = arrayListOf(
        User("python","Used for ai and ml","February 1991",R.drawable.img1),
        User("Java","Used for platform independent code"," May 1995",R.drawable.img),
        User("javaScript","Used for Web Development ","4 December 1995",R.drawable.img2),
        User("Kotlin","Used for Android development","15 February 2016 ",R.drawable.img3),
        User("Rust","Used for system programing","May 15, 2015",R.drawable.img4)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView=findViewById<ListView>(R.id.ListView)
        val adapter=MyAdapter(this,programmingDisplayData)
        listView.adapter=adapter
        listView.setOnItemClickListener{ parent,view,pos,id->
            Toast.makeText(this,programmingDisplayData[pos].name,Toast.LENGTH_SHORT).show()
        }

    }



}