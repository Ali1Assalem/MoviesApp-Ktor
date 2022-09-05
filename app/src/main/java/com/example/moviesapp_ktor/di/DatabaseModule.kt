package com.example.moviesapp_ktor.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp_ktor.data.local.BorutoDatabase
import com.example.moviesapp_ktor.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : BorutoDatabase {
        return  Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            Constants.BORUTO_DATABASE
        ).build()
    }

}