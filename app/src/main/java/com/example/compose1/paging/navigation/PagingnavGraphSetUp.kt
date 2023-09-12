package com.example.compose1.paging.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose1.paging.navigation.screens.PagingHomeScreen
import com.example.compose1.paging.navigation.screens.PagingSearchScreen


@Composable
fun PagingNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = PagingScreen.PagingHome.route){
        composable(PagingScreen.PagingHome.route){
            PagingHomeScreen(navHostController)
        }
        composable(PagingScreen.PagingSearch.route){
            PagingSearchScreen(navHostController = navHostController)
        }
    }
}