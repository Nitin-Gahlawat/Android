package com.example.todoapplication.di.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tasks::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TasksDao(): TasksDao
}


