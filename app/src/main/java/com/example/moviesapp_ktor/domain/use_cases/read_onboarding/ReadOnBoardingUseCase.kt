package com.example.moviesapp_ktor.domain.use_cases.read_onboarding

import com.example.moviesapp_ktor.data.Repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase (
    private  val repository: Repository
){
    operator fun invoke():Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}