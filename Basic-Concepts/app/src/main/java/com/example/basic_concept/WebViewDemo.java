package com.example.basic_concept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewDemo extends AppCompatActivity {
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
            w=findViewById(R.id.w);
            w.getSettings().setJavaScriptEnabled(true);
            w.loadUrl("https://www.google.com");
    }
}