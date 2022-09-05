package com.example.moviesapp_ktor.domain.repository

import androidx.paging.PagingData
import com.example.moviesapp_ktor.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllData(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}