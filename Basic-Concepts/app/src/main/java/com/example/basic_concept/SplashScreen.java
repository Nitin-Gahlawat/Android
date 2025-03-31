package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        try {
            Thread.sleep(10*1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Intent i=new Intent(this,HelloWorld.class);
        startActivity(i);
    }
}