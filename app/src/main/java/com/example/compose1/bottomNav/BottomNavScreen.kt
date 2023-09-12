package com.example.compose1.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(
    val route:String,
    val title:String,
    val icon:ImageVector
){
    object Home:BottomNavScreen(
        "home",
        "Home",
        Icons.Default.Home
    )
    object Settings:BottomNavScreen(
        "settings",
        "Settings",
        Icons.Default.Settings
    )
    object Profile:BottomNavScreen(
        "profile",
        "Profile",
        Icons.Default.Person
    )
}
