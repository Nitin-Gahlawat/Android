package com.example.roomdatabaseandroid

import android.app.Application
import android.content.Context
import androidx.room.Room

class MainClass : Application() {

    companion object {
        lateinit var database: ContactDB
        fun getDb(
            context: Context
        ) {
            database =
                Room.databaseBuilder(context, ContactDB::class.java, "contactDB")
                    .fallbackToDestructiveMigration().build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        getDb(
            context = this
        )
    }

}
