package com.example.compose1.tablayout

import androidx.compose.ui.graphics.Color

sealed class TabScreens(val title:String,val color: Color){
    object Home:TabScreens("Home", Color.Blue)
    object Settings:TabScreens("Settings", Color.Red)
    object Profile:TabScreens("Profile",Color.Green)
}
