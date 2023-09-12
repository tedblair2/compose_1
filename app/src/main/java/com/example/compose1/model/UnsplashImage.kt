package com.example.compose1.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.compose1.util.Util.UNSPLASH_IMAGE_TABLE


@Entity(tableName = UNSPLASH_IMAGE_TABLE)
@kotlinx.serialization.Serializable
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @Embedded
    val urls:Url,
    val likes:Int,
    @Embedded
    val user:User
)
