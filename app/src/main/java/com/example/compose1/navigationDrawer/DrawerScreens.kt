package com.example.compose1.navigationDrawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DrawerScreenExample(drawerScreen: DrawerScreen,onBackPressed:()->Unit) {
    BackHandler(true) {
        onBackPressed()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = drawerScreen.color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = drawerScreen.title, fontSize = 55.sp, fontWeight = FontWeight.Bold)
        Image(imageVector = drawerScreen.imageVector, contentDescription =null )
    }
}