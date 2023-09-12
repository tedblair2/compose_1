package com.example.compose1.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController){
    navigation(startDestination = Screen.Home.route, route = HOME){
        composable(route = Screen.Home.route){
            HomeScreen(navHostController)
        }
        composable(route = Screen.Detail.route,
            arguments = listOf(
                navArgument(ARG_1){
                    type= NavType.StringType
                }
            )){
            val name=it.arguments?.getString(ARG_1).toString()
            DetailScreen(navHostController,name)
        }
    }
}