package com.example.compose1.paging.navigation.screens

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose1.paging.PagingViewModel
import com.example.compose1.paging.SearchViewModel
import com.example.compose1.search.SearchAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun PagingSearchScreen(
    navHostController: NavHostController,
    viewModel: SearchViewModel= koinViewModel()) {

    val searchText by viewModel.searchText
    val searchImages = viewModel.searchedImages.collectAsLazyPagingItems()
    val context=LocalContext.current

    Scaffold(topBar = {
        SearchAppBar(
            text = searchText,
            onTextChange ={ viewModel.setSearchText(it) } ,
            onCloseClicked = { navHostController.popBackStack() },
            onSearchClicked ={
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.searchImages(it)
            } )
    }) {
        PagingItemsColumn(unsplashImages = searchImages)
    }

}
