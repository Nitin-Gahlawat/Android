package com.example.fetchbox.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomClassCartdb::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): RoomCartDbDao
}