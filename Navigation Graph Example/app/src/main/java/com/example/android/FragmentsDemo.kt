package com.example.android

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.android.databinding.ActivityFragementsDemoBinding
import com.example.android.fragments.Cart
import com.example.android.fragments.Home
import com.example.android.fragments.More
import com.example.android.fragments.Payment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class FragmentsDemo : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private var currFrame = 0
    private lateinit var bottomNv: BottomNavigationView
    private var mapFrame:
            Map<Int, Pair<Fragment, Int>> = mapOf(
        0 to Pair(Home(), R.id.Home),
        1 to Pair(Cart(), R.id.Cart),
        2 to Pair(Payment(), R.id.Payment),
        3 to Pair(More(), R.id.More)
    )


    private fun next() {
        currFrame = (currFrame + 1) % 4
        addFragment(mapFrame[currFrame]?.first)
        bottomNv.setSelectedItemId(mapFrame[currFrame]?.second!!);
    }

    private fun prev() {
        if (currFrame == 0) {
            currFrame = 3
        } else {
            currFrame = (currFrame - 1) % 4
        }
        addFragment(mapFrame[currFrame]?.first)
        bottomNv.setSelectedItemId(mapFrame[currFrame]?.second!!);


    }

    private fun addFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentFrame, fragment!!);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityFragementsDemoBinding =
            ActivityFragementsDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(Home())

        bottomNv = binding.bottomNavigationView
        binding.next.setOnClickListener {
            next()
        }
        binding.prev.setOnClickListener {
            prev()
        }

        bottomNv.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Home -> {
                currFrame = 0
                addFragment(Home())
            }

            R.id.Cart -> {
                currFrame = 1
                addFragment(Cart())
            }

            R.id.Payment -> {
                currFrame = 2
                addFragment(Payment())
            }

            R.id.More -> {
                currFrame = 3
                addFragment(More())
            }

            else -> {

            }
        }
        return true
    }

}


