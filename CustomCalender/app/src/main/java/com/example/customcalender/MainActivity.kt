package com.example.customcalender

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.customcalender.databinding.ActivityMainBinding
import java.time.Month
import java.time.YearMonth
import java.util.Calendar


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    private var calendar: Calendar = Calendar.getInstance()

    private fun generateData(): ArrayList<String> {

        val monthNow: Int = calendar.get(Calendar.MONTH)
        val yearNow = calendar.get(Calendar.YEAR)



        val remove: Int = Calendar.Builder().setDate(yearNow, monthNow, 1).build().get(Calendar.DAY_OF_WEEK)
        val x: ArrayList<String> = arrayListOf()



        for (i in 1 until remove) {
            x.add(" ")
        }

        val yearMonth = YearMonth.of(yearNow, monthNow + 1)
        for (i in 1..yearMonth.lengthOfMonth()) {
            x.add("$i")
        }
        for (i in x.size until 35) {
            x.add(" ")
        }
        return x
    }

    private fun updateTextFields(calendar: Calendar, month: TextView, year: TextView) {
        val monthNow: Int = calendar.get(Calendar.MONTH)
        val yearNow = calendar.get(Calendar.YEAR)
        month.text = Month.of(monthNow + 1).name
        year.text = yearNow.toString()
    }


    fun updateDate(s: String) {
        binding.dateDisplay.text =
            " $s /${Month.of(calendar.get(Calendar.MONTH) + 1).name}/ ${calendar.get(Calendar.YEAR)}"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.weekRView.adapter = DisplayDaysName(daysOfWeek)

        val adapter = RecyclerViewAdapter(::updateDate).apply {
            selectedDate = Calendar.Builder().setDate(Calendar.MONTH, Calendar.YEAR, 1).build()
                .get(Calendar.DAY_OF_WEEK) + 1 + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            submitList(generateData())
        }

        binding.newCalander.adapter = adapter
        updateTextFields(calendar, binding.MonthName, binding.YearName)

        binding.prev.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            updateTextFields(calendar, binding.MonthName, binding.YearName)
            adapter.submitList(generateData())
        }
        binding.next.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            updateTextFields(calendar, binding.MonthName, binding.YearName)
            adapter.submitList(generateData())
        }

//        binding.dateDisplay


    }
}