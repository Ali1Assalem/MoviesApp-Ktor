package com.example.moviesapp_ktor.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp_ktor.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel(){

    fun saveOnBoardingState(completed:Boolean) {
        viewModelScope.launch (Dispatchers.IO){
            useCases.saveOnBoardingUseCase(completed)
        }
    }
}