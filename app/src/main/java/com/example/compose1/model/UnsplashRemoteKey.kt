package com.example.compose1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.compose1.util.Util

@Entity(tableName = Util.REMOTE_KEYS_TABLE)
data class UnsplashRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?
)
