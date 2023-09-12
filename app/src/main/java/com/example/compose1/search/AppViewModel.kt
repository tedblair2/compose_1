package com.example.compose1.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AppViewModel:ViewModel() {
    private val _searchWidgetState= mutableStateOf(SearchWidget.CLOSED)
    val searchWidgetState:State<SearchWidget> = _searchWidgetState

    private val _searchTextState= mutableStateOf("")
    val searchTextState:State<String> = _searchTextState


    fun setSearchWidgetState(newSearchWidget: SearchWidget){
        _searchWidgetState.value=newSearchWidget
    }

    fun setSearchTextState(searchTxt:String){
        _searchTextState.value=searchTxt
    }
}