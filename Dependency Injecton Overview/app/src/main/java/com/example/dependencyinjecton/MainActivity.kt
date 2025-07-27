package com.example.dependencyinjecton

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var c: Car


    @Inject
    lateinit var comp:Computer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,c.getCar(),Toast.LENGTH_SHORT).show()


        Toast.makeText(this,comp.getComputer(),Toast.LENGTH_SHORT).show()

    }
}