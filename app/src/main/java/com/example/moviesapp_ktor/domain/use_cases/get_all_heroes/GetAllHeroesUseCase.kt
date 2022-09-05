package com.example.moviesapp_ktor.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.moviesapp_ktor.data.Repository.Repository
import com.example.moviesapp_ktor.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase (
    private val repository: Repository
){
    operator fun invoke():Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}