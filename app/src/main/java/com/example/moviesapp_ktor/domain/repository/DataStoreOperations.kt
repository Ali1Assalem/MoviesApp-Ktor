package com.example.moviesapp_ktor.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed:Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}