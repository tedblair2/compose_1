package com.example.compose1.onBoarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun OnBoardingNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination =OnBoardingScreens.Email.route){
        composable(OnBoardingScreens.Email.route){

        }
        composable(route = OnBoardingScreens.Person.route){

        }
        composable(route = OnBoardingScreens.Settings.route){

        }
    }

}