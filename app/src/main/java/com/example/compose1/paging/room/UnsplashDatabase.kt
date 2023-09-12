package com.example.compose1.paging.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compose1.model.UnsplashImage
import com.example.compose1.model.UnsplashRemoteKey

@Database(entities = [UnsplashImage::class,UnsplashRemoteKey::class], version = 1)
abstract class UnsplashDatabase:RoomDatabase() {

    abstract fun unSplashDao():UnsplashDao
    abstract fun unSplashRemoteKeys():UnsplashRemoteKeysDao
}