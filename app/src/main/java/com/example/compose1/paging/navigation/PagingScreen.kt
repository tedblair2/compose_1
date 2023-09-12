package com.example.compose1.paging.navigation

sealed class PagingScreen(val route:String){
    object PagingHome:PagingScreen("paging_home")
    object PagingSearch:PagingScreen("paging_search")
}
