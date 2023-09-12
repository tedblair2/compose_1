package com.example.compose1.paging.remote

import com.example.compose1.model.SearchResult
import com.example.compose1.model.UnsplashImage

interface ApiService {

    suspend fun getImages(page:Int,per_page:Int):List<UnsplashImage>

    suspend fun searchImages(query: String,per_page: Int):SearchResult?
}