package com.example.compose1.paging.room

import androidx.room.*
import com.example.compose1.model.UnsplashRemoteKey


@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id:String):UnsplashRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys:List<UnsplashRemoteKey>)

    @Query("DELETE FROM unsplash_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}