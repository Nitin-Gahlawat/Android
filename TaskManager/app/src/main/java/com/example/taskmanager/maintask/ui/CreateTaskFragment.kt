package com.example.taskmanager.maintask.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.taskmanager.databinding.FragmentCreateTaskBinding
import com.example.taskmanager.maintask.di.TaskDao
import com.example.taskmanager.maintask.di.TaskTable
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar
import javax.inject.Inject


@SuppressLint("NewApi")
@AndroidEntryPoint
class CreateTaskFragment : DialogFragment() {


    @Inject
    lateinit var db: TaskDao
    private lateinit var binding: FragmentCreateTaskBinding
    val currLocalDateTime = LocalDateTime.now()

    //Time Picker
    @SuppressLint("SetTextI18n")
    private fun setTime() {
        var time = binding.time

        time.isFocusable = false
        time.isClickable = true
        time.isCursorVisible = false

        time.setText(" ${currLocalDateTime.hour} : ${currLocalDateTime.minute}")

        val picker = MaterialTimePicker.Builder().apply {
            setTimeFormat(TimeFormat.CLOCK_24H)
            setHour(currLocalDateTime.hour)
            setMinute(currLocalDateTime.minute)
            setTitleText("Enter the task time")
        }.build()

        picker.addOnPositiveButtonClickListener {
            binding.time.setText(" ${picker.hour} : ${picker.minute}")
        }

        this.binding.time.setOnClickListener {
            fragmentManager?.let { it1 -> picker.show(it1, "tag") };
        }

    }

    private fun setDate() {
        var date = binding.date

        date.isFocusable = false
        date.isClickable = true
        date.isCursorVisible = false
        date.setText("${currLocalDateTime.dayOfMonth}/${currLocalDateTime.month.value}/${currLocalDateTime.year}")

        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Select the TODO Date")
                .setCalendarConstraints(
                    CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now())
                        .build()
                ).build()


        this.binding.date.setOnClickListener {
            fragmentManager?.let { it1 -> datePicker.show(it1, "tag") };
        }
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1
            val year = calendar.get(Calendar.YEAR)
            date.setText("$day/$month/$year")
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        this.binding = FragmentCreateTaskBinding.inflate(layoutInflater)

        setTime()
        setDate()

        this.binding.dateParent.visibility = View.GONE
        this.binding.timeParent.visibility = View.VISIBLE

        this.binding.isTimed.setOnClickListener {
            if (this.binding.isTimed.isChecked) {
                this.binding.timeParent.visibility = View.GONE
                this.binding.dateParent.visibility = View.VISIBLE
            } else {
                this.binding.dateParent.visibility = View.GONE
                this.binding.timeParent.visibility = View.VISIBLE
            }
        }



        this.binding.AddTask.setOnClickListener {
            val heading = binding.heading.text.toString()
            val body = binding.body.text.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                this@CreateTaskFragment.db.insert(TaskTable(0,false, heading, body))
            }.start()
            dismiss()
        }
        this.binding.cancelButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}
