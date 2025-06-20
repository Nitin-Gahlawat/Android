package com.example.roomdatabaseandroid

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Contact::class], version = 2)
abstract class ContactDB : RoomDatabase() {

    abstract fun contactDao():ContactDao
}