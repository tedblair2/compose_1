package com.example.compose1.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController){
    navigation(startDestination = Screen.Login.route, route = AUTH){
        composable(Screen.Login.route){
            Login(navHostController)
        }
        composable(Screen.SignUp.route){
            SignUp(navHostController)
        }
    }
}