package com.example.compose1.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SearchResult(
    @SerialName("results")
    val results:List<UnsplashImage>
)
