package com.example.compose1.model

import androidx.room.Embedded

@kotlinx.serialization.Serializable
data class User(
    val username:String,
    @Embedded
    val links: UserLinks
)