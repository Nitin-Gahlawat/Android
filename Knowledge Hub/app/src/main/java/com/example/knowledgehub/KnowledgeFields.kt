package com.example.knowledgehub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KnowledgeFields : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge_fields)

        val allElementsBtn =
            arrayOf(R.id.Android, R.id.Ml, R.id.Ios, R.id.Web, R.id.BlockChain, R.id.CyberSecurity)
        for (i in allElementsBtn) {
            findViewById<Button>(i).setOnClickListener {
                val intent: Intent = Intent(this, DisplayData::class.java)
                Log.d("fields", "Id before sending to other Activity: $i ")
                intent.putExtra("id",i)
                startActivity(intent)
            }
        }
        findViewById<Button>(R.id.contact).setOnClickListener {
            Toast.makeText(this,"Thank you for contacting us",Toast.LENGTH_LONG).show();

        }


    }
}