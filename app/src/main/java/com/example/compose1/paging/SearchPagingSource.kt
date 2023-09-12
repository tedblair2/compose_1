package com.example.compose1.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.compose1.model.UnsplashImage
import com.example.compose1.paging.remote.ApiService

class SearchPagingSource(
    private val apiService: ApiService,
    private val query:String
):PagingSource<Int,UnsplashImage>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage=params.key ?: 1
        return try {
            val response=apiService.searchImages(query,params.loadSize)
            response?.let {
                if (it.results.isNotEmpty()){
                    LoadResult.Page(
                        data = it.results,
                        prevKey = if (currentPage == 1) null else currentPage.minus(1),
                        nextKey = if (it.results.size < 10) null else currentPage.plus(1)
                    )
                }else{
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            } ?: LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}