package com.example.moviesapp_ktor.di

import android.content.Context
import com.example.moviesapp_ktor.data.Repository.DataStoreOperationsImpl
import com.example.moviesapp_ktor.data.Repository.Repository
import com.example.moviesapp_ktor.domain.repository.DataStoreOperations
import com.example.moviesapp_ktor.domain.use_cases.UseCases
import com.example.moviesapp_ktor.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.moviesapp_ktor.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository):UseCases {
        return UseCases(
            SaveOnBoardingUseCase(repository),
            ReadOnBoardingUseCase(repository)
        )
    }

}