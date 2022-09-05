package com.example.moviesapp_ktor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapp_ktor.data.local.dao.HeroDao
import com.example.moviesapp_ktor.data.local.dao.HeroRemoteKeysDao
import com.example.moviesapp_ktor.domain.model.Hero
import com.example.moviesapp_ktor.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeysDao

}