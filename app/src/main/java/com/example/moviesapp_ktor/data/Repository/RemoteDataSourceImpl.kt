package com.example.moviesapp_ktor.data.Repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.RoomDatabase
import com.example.moviesapp_ktor.data.local.BorutoDatabase
import com.example.moviesapp_ktor.data.local.dao.HeroDao
import com.example.moviesapp_ktor.data.paging_source.HeroRemoteMediator
import com.example.moviesapp_ktor.data.remote.MoviesApi
import com.example.moviesapp_ktor.domain.model.Hero
import com.example.moviesapp_ktor.domain.repository.RemoteDataSource
import com.example.moviesapp_ktor.util.Constants
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val moviesApi: MoviesApi,
    private val database: BorutoDatabase
) : RemoteDataSource{

    // Single Of Truth is BorutoDatabase

    private val heroDao = database.heroDao()

    override fun getAllData(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = {heroDao.getAllHeroes()}
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                moviesApi,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow  //return aflow of a paging Data of type Hero
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}