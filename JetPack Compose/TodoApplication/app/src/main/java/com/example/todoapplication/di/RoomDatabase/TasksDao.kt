package com.example.todoapplication.di.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query

@Dao
interface TasksDao {

    @Insert
    suspend fun insert(task: Tasks)


    @Delete
    suspend fun delete(task:Tasks)

    @Update
    suspend fun update(task:Tasks)


    @Query("SELECT * FROM Tasks WHERE id = :userId")
    suspend fun getTasksById(userId: Int): Tasks?

    @Query("SELECT * FROM Tasks")
    suspend fun getAllTasks(): List<Tasks>
}
