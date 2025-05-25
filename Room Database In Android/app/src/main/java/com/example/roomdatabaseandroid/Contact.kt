package com.example.roomdatabaseandroid
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "Contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var name:String,
    var phone:String,
)


@Dao
interface ContactDao{
    @Insert
    suspend fun insertContact(con:Contact):Long

    @Delete
    suspend fun deleteContact(con:Contact)

    @Update
    suspend fun updateContact(con:Contact)

    @Query("select * from Contact")
    suspend fun selectContact():List<Contact>



    @Query("delete from Contact where id=:id")
    suspend fun customDeleteInfo(id:Int)
}