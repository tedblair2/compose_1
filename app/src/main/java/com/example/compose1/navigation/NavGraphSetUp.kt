package com.example.compose1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraphSetUp(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Splash.route, route = ROOT){
        composable(Screen.Splash.route){
            AnimatedSplashScreen(navHostController = navHostController)
        }
        homeNavGraph(navHostController)
        authNavGraph(navHostController)
    }
}