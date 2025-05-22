package com.example.fragmentbasics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragmentbasics.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    private fun changeFrameToFragment(x:Fragment){
        var FMvar: FragmentManager =supportFragmentManager
        var FMTransaction: FragmentTransaction =FMvar.beginTransaction()
        FMTransaction.replace(R.id.frameLayout,x)
        FMTransaction.commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFrameToFragment(AnalogFragment())

        findViewById<AppCompatButton>(R.id.btnTime).setOnClickListener {
            changeFrameToFragment(AnalogFragment())
        }
        findViewById<AppCompatButton>(R.id.btnPhoto).setOnClickListener {
            changeFrameToFragment(ImageFragment())
        }
        findViewById<AppCompatButton>(R.id.Login).setOnClickListener {
            changeFrameToFragment(LoginFragment())
        }

    }
}