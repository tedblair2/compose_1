package com.example.compose1.paging.remote

import com.example.compose1.model.SearchResult
import com.example.compose1.model.UnsplashImage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ApiServiceImpl(private val client:HttpClient) : ApiService {

    override suspend fun getImages(page: Int, per_page: Int): List<UnsplashImage> {
        return try {
            client.get("/photos"){
                parameter("page",page)
                parameter("per_page",per_page)
            }.body()
        }catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun searchImages(query: String, per_page: Int): SearchResult? {
        return try {
            client.get("/search/photos"){
                parameter("per_page",per_page)
                parameter("query",query)
            }.body()
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }
}