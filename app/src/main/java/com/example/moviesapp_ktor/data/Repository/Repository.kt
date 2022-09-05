package com.example.moviesapp_ktor.data.Repository

import androidx.paging.PagingData
import com.example.moviesapp_ktor.domain.model.Hero
import com.example.moviesapp_ktor.domain.repository.DataStoreOperations
import com.example.moviesapp_ktor.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val remote : RemoteDataSource
) {

    fun getAllHeroes() : Flow<PagingData<Hero>> {
        return remote.getAllData()
    }

    suspend fun saveOnBoardingState(completed : Boolean){
        dataStoreOperations.saveOnBoardingState(completed)
    }

    fun readOnBoardingState() : Flow<Boolean> {
        return dataStoreOperations.readOnBoardingState()
    }
}