package com.example.diloageboxes

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.diloageboxes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Are you Sure?")
            builder1.setMessage("Do you Want to close the App?")
            builder1.setIcon(R.drawable.baseline_exit_to_app_24)
            builder1.setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                    finish()
                })
            builder1.setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                })
            builder1.show()
        }

        binding.button2.setOnClickListener {

            val array = arrayOf("java", "kotlin", "python", "c", "c++")
            val builder2 = AlertDialog.Builder(this)
            builder2.setTitle("Which is your favourite language");
            builder2.setSingleChoiceItems(
                array,
                0,
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, which: Int ->
                    Toast.makeText(this, "you have clicked ${array[which]}", Toast.LENGTH_LONG)
                        .show()
                })
            builder2.setPositiveButton(
                "Accept",
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->

                })
            builder2.setNegativeButton(
                "Decline",
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                })
            builder2.show()
        }
    }
}

