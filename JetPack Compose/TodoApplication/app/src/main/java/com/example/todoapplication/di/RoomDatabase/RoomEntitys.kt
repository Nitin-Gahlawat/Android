package com.example.todoapplication.di.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "Completed") val isCompleted: Boolean
)
