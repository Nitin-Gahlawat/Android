package com.example.nestedrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nestedrecyclerview.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {



        fun getData():ArrayList<ArrayList<Int>>{
            var data= arrayListOf<ArrayList<Int>>()
            for (i in 1..8){
                var temp= arrayListOf<Int>()
                for(j in 0..19){
                    temp.add(Random.nextInt(4))
                }
                data.add(temp)
            }
            return data
        }



    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRView.layoutManager=LinearLayoutManager(this)

        var adapter=MainAdapter(getData())
        binding.mainRView.adapter=adapter





    }
}