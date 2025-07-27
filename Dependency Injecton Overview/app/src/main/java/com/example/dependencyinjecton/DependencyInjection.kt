package com.example.dependencyinjecton

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {

    @Provides
    @Singleton
    fun provideRam() : Ram{
        return  Ram()
    }

    @Provides
    @Singleton
    fun abc(
        ram: Ram
    ): Computer {
        return Computer(Ram())
    }
}