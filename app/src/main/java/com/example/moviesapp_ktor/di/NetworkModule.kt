package com.example.moviesapp_ktor.di

import androidx.paging.ExperimentalPagingApi
import com.example.moviesapp_ktor.data.Repository.RemoteDataSourceImpl
import com.example.moviesapp_ktor.data.local.BorutoDatabase
import com.example.moviesapp_ktor.data.remote.MoviesApi
import com.example.moviesapp_ktor.domain.repository.RemoteDataSource
import com.example.moviesapp_ktor.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.MINUTES)// is The Timeout on waiting to read the data
            .connectTimeout(15,TimeUnit.MINUTES)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit {
        val contentType = MediaType.get("application/json")
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))//Kotlinx Serialization library
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit):MoviesApi{
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        moviesApi: MoviesApi,
        database: BorutoDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            moviesApi,
            database
        )
    }

}