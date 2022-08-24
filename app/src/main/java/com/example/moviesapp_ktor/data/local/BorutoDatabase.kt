package com.example.moviesapp_ktor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapp_ktor.data.local.dao.HeroDao
import com.example.moviesapp_ktor.data.local.dao.HeroRemoteKeyDao
import com.example.moviesapp_ktor.domain.model.Hero
import com.example.moviesapp_ktor.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao

}