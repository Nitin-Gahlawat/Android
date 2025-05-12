package com.example.praticeapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

object City {
    var Mumbai = "Mumbai"
    var Pune = "Pune"
    var Delhi = "Delhi"
    var Agra = "Agra"
    var Chennai = "Chennai"
    var HongKong = "HongKong"
    var London = "London"
    var NewYork = "NewYork"
    var Paris = "Paris"
}

class ExplicitIntent : AppCompatActivity() {

    private var loc = mapOf(
        City.Mumbai to Pair(19.0760, 72.8777),
        City.Agra to Pair(27.1767, 78.0081),
        City.Pune to Pair(18.5204, 73.8567),
        City.Delhi to Pair(28.7041, 77.1025),
        City.Paris to Pair(48.8575, 2.3514),
        City.Chennai to Pair(13.0843, 80.2705),
        City.HongKong to Pair(22.3193, 114.1694),
        City.London to Pair(51.5072, 0.1276),
        City.NewYork to Pair(40.7128, -74.0060)

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var ll = LinearLayout(this)
        ll.orientation = LinearLayout.VERTICAL
        ll.gravity = Gravity.CENTER
        ll.setPadding(50)


        loc = loc.toSortedMap()

        for ((key, value) in loc) {
            val location = "geo:${value.first},${value.second}"
            ll.addView(Button(this@ExplicitIntent).apply {
                text = key
                setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(location)))
                }
            })
        }
        setContentView(ll)

    }

}