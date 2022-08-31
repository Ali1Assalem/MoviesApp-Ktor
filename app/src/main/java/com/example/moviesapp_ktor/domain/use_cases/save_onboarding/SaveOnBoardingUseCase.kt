package com.example.moviesapp_ktor.domain.use_cases.save_onboarding

import com.example.moviesapp_ktor.data.Repository.Repository

class SaveOnBoardingUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(completed : Boolean){
        repository.saveOnBoardingState(completed)
    }
}