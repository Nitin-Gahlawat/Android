package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class SwitchExample extends AppCompatActivity implements View.OnClickListener {

    Switch a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_example);
        a=findViewById(R.id.switch1);
    }

    public void onClick(View view) {
        String str;
        if(a.isChecked())
            str="switch on";
        else
            str="switch off";
        android.widget.Toast t= android.widget.Toast.makeText(this,str, Toast.LENGTH_SHORT);
        t.show();
    }
}