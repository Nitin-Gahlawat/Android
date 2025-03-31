package com.example.basic_concept

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basic_concept.databinding.ActivityBasicsViewsBinding


class BasicsViews : AppCompatActivity() {
    lateinit var binding: ActivityBasicsViewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBasicsViewsBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()


        // Setting The Text in the Spinner
        val languages = arrayOf(
            "Python",
            "Java",
            "JavaScript",
            "Kotlin",
            "C",
            "C++",
            "Ruby",
            "Swift",
            "Go",
            "PHP",
            "TypeScript",
            "Rust",
            "Scala",
            "R",
            "Objective-C"
        )
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.adapter = adapter


//        Getting the values of various fields
        var gender: String = ""
        binding.RadioGroup.setOnCheckedChangeListener { group, checkedId -> // Find the selected radio button
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            gender = selectedRadioButton.text.toString()
        }
        var selectedText = ""
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long
            ) {
                selectedText = parentView.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                selectedText = ""
            }

        }

        // Set a listener to listen for changes
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                if (binding.switch1.isChecked){
                    binding.button.textSize = progress.toFloat()
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        //  Setting the Onclick Listener on the final Button
        binding.button.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.visibility = View.GONE;
            }, 3000)


            if (binding.checkBox.isChecked) {
                var name: String = binding.name.text.toString()
                binding.spinner.adapter
                AlertDialog.Builder(this).apply {
                    setTitle("Send Data")
                    setMessage("Your name is: $name ,Your Gender is:$gender,Your Favorite language $selectedText")
                }.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    }).show()
            } else {
                Toast.makeText(this@BasicsViews, "Check to sumbit the data", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }
}