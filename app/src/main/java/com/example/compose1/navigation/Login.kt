package com.example.compose1.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun Login(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Login",
                fontSize = 30.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                })
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {

                Text(
                    text = "sign up",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        navHostController.navigate(Screen.SignUp.route)
                    })
                Text(
                    text = "Go Home",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        navHostController.navigate(HOME){
                            popUpTo(navHostController.graph.id){
                                inclusive=true
                            }
                        }
                    })
            }
        }
    }
}