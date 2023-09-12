package com.example.compose1.paging.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.compose1.model.UnsplashImage

@Dao
interface UnsplashDao {

    @Query("SELECT * FROM splash_table")
    fun getAllImages():PagingSource<Int,UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images:List<UnsplashImage>)

    @Query("DELETE FROM splash_table")
    suspend fun deleteImages()
}