package com.example.contentprovider

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class UserDataGet : AppCompatActivity() {

    private val REQUEST_CODE_PICK_FILE = 1

    private fun pickFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUEST_CODE_PICK_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PICK_FILE) {
            val uri: Uri? = data?.data
            if (uri != null) {
                handleSelectedFile(uri)
            }
        }
    }


    private fun handleSelectedFile(uri: Uri) {
        contentResolver.openInputStream(uri)?.use { inputStream ->

            findViewById<TextView>(R.id.toGetData).text  = inputStream.bufferedReader().readText()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data_get)
        findViewById<AppCompatButton>(R.id.GetDataButton)
            .setOnClickListener {
                pickFile()
            }
    }
}