package com.example.taskmanager.maintask.di


import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Entity(tableName = "TaskTable")
data class TaskTable(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "IsSelected") var selected:Boolean?=false,
    @ColumnInfo(name = "TaskOverview") val heading: String?,
    @ColumnInfo(name = "TaskDescription") val body: String?,
)



@Database(entities = [TaskTable::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTasks(): TaskDao
}

@Dao
interface TaskDao {
    // Get all tasks from TaskTable ordered by some field (e.g., id or created_at)
    @Query("SELECT * FROM TaskTable")
    fun getAll(): Flow<List<TaskTable>>

    // Insert a task into the database
    @Insert
    fun insert(p: TaskTable)


    @Delete
    fun Delete(p:TaskTable)
}

@Module
@InstallIn(SingletonComponent::class)
object GetDbRef {

    @Provides
    @Singleton
    fun getDB(
        @ApplicationContext application: Context
    ): TaskDao {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "PicturesDB"
        ).build().getTasks()
    }
}
