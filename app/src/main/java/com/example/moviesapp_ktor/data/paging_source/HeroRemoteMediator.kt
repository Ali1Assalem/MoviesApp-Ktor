package com.example.moviesapp_ktor.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviesapp_ktor.data.local.BorutoDatabase
import com.example.moviesapp_ktor.data.remote.MoviesApi
import com.example.moviesapp_ktor.domain.model.Hero
import com.example.moviesapp_ktor.domain.model.HeroRemoteKeys
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val moviesApi: MoviesApi,
    private val database: BorutoDatabase
): RemoteMediator<Int, Hero>() {

    private val heroDao = database.heroDao()
    private val heroRemoteKeysDao = database.heroRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try{

            val page = when (loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?:1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys !=null
                        )
                    nextPage
                }
            }


            val response = moviesApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()){
                database.withTransaction {
                    if (loadType == LoadType.REFRESH){ //Clear DB table
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            hero.id,
                            prevPage=prevPage,
                            nextPage=nextPage
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKeys(keys)
                    heroDao.addHeroes(response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        }
        catch (e:Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeysDao.getRemoteKeys(hero.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
            heroRemoteKeysDao.getRemoteKeys(hero.id)
        }// get the first item from that list
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

}