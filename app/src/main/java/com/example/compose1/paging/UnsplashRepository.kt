package com.example.compose1.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.compose1.model.UnsplashImage
import com.example.compose1.paging.remote.ApiService
import com.example.compose1.paging.room.UnsplashDatabase
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class UnsplashRepository(
    private val apiService: ApiService,
    private val unsplashDatabase: UnsplashDatabase
) {

    fun getAllImages():Flow<PagingData<UnsplashImage>>{
        val pagingSourceFactory={ unsplashDatabase.unSplashDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = UnsplashRemoteMediator(
                apiService, unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
    fun getSearchImages(query:String):Flow<PagingData<UnsplashImage>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchPagingSource(apiService,query) }
        ).flow
    }
}