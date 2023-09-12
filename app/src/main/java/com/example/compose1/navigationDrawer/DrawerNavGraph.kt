package com.example.compose1.navigationDrawer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DrawerNavGraph(navHostController: NavHostController,onBackPressed:()->Unit) {
    NavHost(navController = navHostController, startDestination = DrawerScreen.Home.route){
        composable(DrawerScreen.Home.route){
            DrawerScreenExample(drawerScreen = DrawerScreen.Home,onBackPressed)
        }
        composable(DrawerScreen.Profile.route){
            DrawerScreenExample(drawerScreen = DrawerScreen.Profile,onBackPressed)
        }
        composable(DrawerScreen.Settings.route){
            DrawerScreenExample(drawerScreen = DrawerScreen.Settings,onBackPressed)
        }
    }
}