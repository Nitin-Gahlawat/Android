package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class RadioButtonExample extends AppCompatActivity {
    EditText e;
    CheckBox c1;
    RadioButton r1,r2,r3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_example);
        c1=findViewById(R.id.checkBox);
        r1=findViewById(R.id.Python);
        r2=findViewById(R.id.php);
        r3=findViewById(R.id.Java);
        e=findViewById(R.id.textbox);
    }

    public void Onclick(View v){
        if(c1.isChecked()){
            String str="";
            if(v.getId()==R.id.Python){
                str=r1.getText().toString();
            } else if (v.getId()==R.id.php) {
                str=r2.getText().toString();
            }else if (v.getId()==R.id.Java) {
                str=r3.getText().toString();
            }
            e.setText(str);

        }else {
            e.setText("");
        }
    }
}