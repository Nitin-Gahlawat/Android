package com.example.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpager.databinding.ActivityMainBinding
import com.example.viewpager.fragments.Fragment1
import com.example.viewpager.fragments.Fragment2
import com.example.viewpager.fragments.Fragment3
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        var viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(Fragment1(), "Fragment 1")
        viewPagerAdapter.addFragment(Fragment2(), "Fragment 2")
        viewPagerAdapter.addFragment(Fragment3(), "Fragment 3")

        binding.pager.adapter = viewPagerAdapter


        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
        }.attach()

    }
}