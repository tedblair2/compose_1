package com.example.compose1.paging

import androidx.lifecycle.ViewModel

class PagingViewModel(
    private val unsplashRepository: UnsplashRepository
):ViewModel() {

    val allImages=unsplashRepository.getAllImages()

}