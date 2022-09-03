package com.example.moviesapp_ktor.domain.use_cases.read_onboarding

import android.util.Log
import com.example.moviesapp_ktor.data.Repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor(
    private  val repository: Repository
){
    operator fun invoke():Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}