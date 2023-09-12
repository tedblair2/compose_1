package com.example.compose1.tablayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TabScreen(tabScreen: TabScreens) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = tabScreen.color),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = tabScreen.title, fontSize = 55.sp, fontWeight = FontWeight.Bold)
    }
}