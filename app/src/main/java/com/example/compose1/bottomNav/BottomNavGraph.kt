package com.example.compose1.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomNavScreen.Home.route){
        composable(route = BottomNavScreen.Home.route){
            HomeBottomNav()
        }
        composable(route = BottomNavScreen.Settings.route){
            SettingsBottomNav()
        }
        composable(route = BottomNavScreen.Profile.route){
            ProfileBottomNav()
        }

    }
}