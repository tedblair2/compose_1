package com.example.compose1.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.compose1.model.UnsplashImage
import com.example.compose1.model.UnsplashRemoteKey
import com.example.compose1.paging.remote.ApiService
import com.example.compose1.paging.room.UnsplashDatabase

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator(
    private val apiService: ApiService,
    private val unsplashDatabase: UnsplashDatabase
):RemoteMediator<Int,UnsplashImage>() {

    private val unsplashDao=unsplashDatabase.unSplashDao()
    private val unsplashRemoteKeysDao=unsplashDatabase.unSplashRemoteKeys()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try {
            val currentPage=when(loadType){
                LoadType.REFRESH->{
                    val remoteKeys=getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND->{
                    val remoteKeys=getRemoteKeyForFirstItem(state)
                    val prevPage= remoteKeys?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND->{
                    val remoteKeys=getRemoteKeyForLastItem(state)
                    val nextPage=remoteKeys?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }
            val response=apiService.getImages(currentPage,10)
            val endOfPaginationReached=response.isEmpty()

            val prevPage=if (currentPage == 1) null else currentPage.minus(1)
            val nextPage=if (endOfPaginationReached) null else currentPage.plus(1)

            unsplashDatabase.withTransaction {
                if (loadType==LoadType.REFRESH){
                    unsplashDao.deleteImages()
                    unsplashRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys=response.map { unsplashImage ->
                    UnsplashRemoteKey(unsplashImage.id,prevPage,nextPage)
                }
                unsplashRemoteKeysDao.insertRemoteKeys(keys)
                unsplashDao.addImages(response)
            }
            MediatorResult.Success(endOfPaginationReached)
        }catch (e:Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UnsplashImage>):UnsplashRemoteKey?{
        return state.anchorPosition?.let {position->
            state.closestItemToPosition(position)?.id?.let {id->
                unsplashRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UnsplashImage>):UnsplashRemoteKey?{
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
            unsplashRemoteKeysDao.getRemoteKeys(it.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UnsplashImage>):UnsplashRemoteKey?{
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            unsplashRemoteKeysDao.getRemoteKeys(it.id)
        }
    }
}