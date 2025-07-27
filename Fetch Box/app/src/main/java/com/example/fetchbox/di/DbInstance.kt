package com.example.fetchbox.di

import android.content.Context
import androidx.room.Room
import com.example.fetchbox.data.AppDatabase
import com.example.fetchbox.data.RoomCartDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetDb {

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): RoomCartDbDao {
         val roomDataBase =Room.databaseBuilder(
            context = context,
             AppDatabase::class.java, "MyCart"
        ).build()
        return roomDataBase.cartDao()
    }
}