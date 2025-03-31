//Intent application
package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.net.Uri;

public class IntentDemo extends AppCompatActivity implements View.OnClickListener {
        Button exp,imp;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intent);
            exp=findViewById(R.id.b1);
            exp.setOnClickListener(this);

            imp=findViewById(R.id.b2);
            imp.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.b1) {
                Intent i=new Intent(this, Addition.class);
                startActivity(i);
            } else if (view.getId() == R.id.b2) {
                Uri u = Uri.parse("https://www.google.com");
                Intent i = new Intent(Intent.ACTION_VIEW, u);
                startActivity(i);
            }
        }
}