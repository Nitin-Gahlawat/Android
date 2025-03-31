package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DateAndTime extends AppCompatActivity {

    DatePicker dp;
    TimePicker tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);
        dp=findViewById(R.id.date);
        tp=findViewById(R.id.time);
    }
}