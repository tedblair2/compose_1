package com.example.compose1.di

import androidx.room.Room
import com.example.compose1.paging.PagingViewModel
import com.example.compose1.paging.SearchViewModel
import com.example.compose1.paging.UnsplashRepository
import com.example.compose1.paging.remote.ApiService
import com.example.compose1.paging.remote.ApiServiceImpl
import com.example.compose1.paging.room.UnsplashDatabase
import com.example.compose1.slidingAnim.SlidingAnimViewModel
import com.example.compose1.util.Util
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    single {
        HttpClient(Android){
            install(Logging){
                level=LogLevel.ALL
            }
            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys=true
                })
            }
            install(DefaultRequest){
                url(Util.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("Authorization","Client-ID ${Util.CLIENT_ID}")
            }
            engine {
                socketTimeout=15_000
                connectTimeout=15_000
            }
        }
    }
    single<ApiService> {
        ApiServiceImpl(get())
    }
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            UnsplashDatabase::class.java,
            Util.DB_NAME).build()
    }
    factory {
        UnsplashRepository(get(),get())
    }
    viewModel {
        PagingViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        SlidingAnimViewModel()
    }
}