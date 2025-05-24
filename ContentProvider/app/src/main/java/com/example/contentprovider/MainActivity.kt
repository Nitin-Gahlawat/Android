package com.example.contentprovider

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import java.io.IOException
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private fun saveTextFile(context: Context, fileName: String, content: String) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Documents/")
        }


        val contentUri: Uri = MediaStore.Files.getContentUri("external")
        val fileUri: Uri? = context.contentResolver.insert(contentUri, contentValues)


        fileUri?.let {
            try {
                context.contentResolver.openOutputStream(fileUri)?.use { outputStream ->
                    outputStream.write(content.toByteArray())  // Write text content to the file
                    outputStream.flush()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var x = findViewById<AppCompatButton>(R.id.toSave)


        x.setOnClickListener {
            val m = Random.nextInt(0, 200)

            Log.d("filenamename", "the random number is $m")
            val filename = "abc${m}.txt"
            val content =
                "This is a abc file used for testing function ${Random.nextInt(0, 200)}"

            saveTextFile(this, filename, content)
            Toast.makeText(
                this@MainActivity,
                "the write Sucessfull in file $filename",
                Toast.LENGTH_LONG
            ).show()
        }

        findViewById<AppCompatButton>(R.id.movebtn).setOnClickListener {
            startActivity(Intent(this, UserDataGet::class.java))
        }

    }
}