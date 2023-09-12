package com.example.compose1.onBoarding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class OnBoardingScreens(
    val route:String,
    val image:ImageVector,
    val title:String,
    val subtitle:String,
    val tint:Color
){
    object Email:OnBoardingScreens(
        route = "email",
        image = Icons.Default.Email,
        title = "This is the Email page",
        subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."+
                "Sed euismod nisl vel mi bibendum, in ullamcorper nunc pellentesque."+
                "Fusce non dui at quam suscipit semper. Proin interdum, purus ac facilisis finibus,"+
                "eros nulla ullamcorper velit, nec blandit dolor velit at odio.",
        tint = Color.Blue
    )

    object Person:OnBoardingScreens(
        route = "person",
        image = Icons.Default.Person,
        title = "This is the Person page",
        subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."+
                "Sed euismod nisl vel mi bibendum, in ullamcorper nunc pellentesque."+
                "Fusce non dui at quam suscipit semper. Proin interdum, purus ac facilisis finibus,"+
                "eros nulla ullamcorper velit, nec blandit dolor velit at odio.",
        tint = Color.Red
    )

    object Settings:OnBoardingScreens(
        route = "settings",
        image = Icons.Default.Settings,
        title = "This is the settings page",
        subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."+
                "Sed euismod nisl vel mi bibendum, in ullamcorper nunc pellentesque."+
                "Fusce non dui at quam suscipit semper. Proin interdum, purus ac facilisis finibus,"+
                "eros nulla ullamcorper velit, nec blandit dolor velit at odio.",
        tint = Color.Green
    )
}
