package com.example.compose1.paging

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose1.model.UnsplashImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val unsplashRepository: UnsplashRepository
):ViewModel() {

    private val _searchText= mutableStateOf("")
    val searchText: State<String> = _searchText


    private val _searchedImages= MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchedImages: StateFlow<PagingData<UnsplashImage>> = _searchedImages

    fun setSearchText(query: String){
        _searchText.value=query
    }

    fun searchImages(query:String){
        viewModelScope.launch {
            unsplashRepository.getSearchImages(query).cachedIn(viewModelScope).collect{
                _searchedImages.value=it
            }
        }
    }
}