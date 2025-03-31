//Toast application

package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class Toast extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        b1=findViewById(R.id.btn);
        b1.setOnClickListener((v)->{
            android.widget.Toast t=android.widget.Toast.makeText(this,"hello world from the toast",android.widget.Toast.LENGTH_LONG);
            t.show();

        });
    }
}