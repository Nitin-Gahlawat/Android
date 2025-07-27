package com.example.fetchbox.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "CartDb")
data class RoomClassCartdb(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val Img: String,
    var title: String,
)


@Dao
interface RoomCartDbDao {
    @Insert
    suspend fun insertCartDb(db: RoomClassCartdb)

    @Query("SELECT * FROM CartDb")
    fun getUser(): Flow<List<RoomClassCartdb>>


    @Query("DELETE FROM CartDb where 1==1")
    suspend fun deleteAllCart()
}