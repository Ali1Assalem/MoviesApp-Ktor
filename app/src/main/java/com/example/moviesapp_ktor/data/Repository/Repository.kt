package com.example.moviesapp_ktor.data.Repository

import com.example.moviesapp_ktor.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed : Boolean){
        dataStoreOperations.saveOnBoardingState(completed)
    }

    fun readOnBoardingState() : Flow<Boolean> {
        return dataStoreOperations.readOnBoardingState()
    }
}