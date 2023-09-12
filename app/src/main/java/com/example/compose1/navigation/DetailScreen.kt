package com.example.compose1.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(navHostController: NavHostController,name:String) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Text(text = "Hello $name",
            fontSize = 30.sp,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold,
        modifier = Modifier.clickable {
            navHostController.navigate(Screen.Home.route){
                popUpTo(Screen.Home.route){
                    inclusive=true
                }
            }
        })
    }
}

@Preview
@Composable
fun DetailPreview(){
    DetailScreen(rememberNavController(),"Ted Blair")
}