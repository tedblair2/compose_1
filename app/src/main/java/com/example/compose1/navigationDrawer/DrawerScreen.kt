package com.example.compose1.navigationDrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerScreen(val title:String,val color: Color,val imageVector: ImageVector, val route:String){
    object Home:DrawerScreen("Home",Color.LightGray,Icons.Default.Home,"drawer_home")
    object Settings:DrawerScreen("Settings", Color.Cyan,Icons.Default.Settings,"drawer_settings")
    object Profile:DrawerScreen("Profile",Color.Yellow,Icons.Default.Person,"drawer_profile")
}
