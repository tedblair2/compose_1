package com.example.compose1.bottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeBottomNav() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center ) {

        Text(text = "Home", fontSize = 40.sp, color = Color.White)
    }
}

@Composable
fun ProfileBottomNav() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center ) {

        Text(text = "Profile", fontSize = 40.sp, color = Color.White)
    }
}

@Composable
fun SettingsBottomNav() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Blue),
        contentAlignment = Alignment.Center ) {

        Text(text = "Settings", fontSize = 40.sp, color = Color.White)
    }
}