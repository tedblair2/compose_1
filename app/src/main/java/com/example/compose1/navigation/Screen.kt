package com.example.compose1.navigation

const val ARG_1="name"

const val ROOT="root_route"
const val HOME="home_route"
const val AUTH="auth_route"

sealed class Screen(val route:String){
    object Home:Screen("home_screen")
    object Detail:Screen("detail_screen/{$ARG_1}"){
        fun passName(name:String):String{
            return "detail_screen/$name"
        }
    }
    object SignUp:Screen("signup")
    object Login:Screen("login")
    object Splash:Screen("splash")
}
