//sum application

package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class Addition extends AppCompatActivity {
    EditText a,b,c;
    Button sum,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        a=findViewById(R.id.text1);
        b=findViewById(R.id.text2);
        c=findViewById(R.id.text3);


        sum=findViewById(R.id.sum);
        clear=findViewById(R.id.clear);

        sum.setOnClickListener((y)->{
            int a_val=Integer.parseInt(String.valueOf(a.getText()));
            int b_val=Integer.parseInt(String.valueOf(b.getText()));
            c.setText(String.valueOf(a_val+b_val));
        });

        clear.setOnClickListener((x)->{
            a.setText("");
            b.setText("");
            c.setText("");
        });

    }
}