package com.example.compose1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(gradient:Brush){
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            contentPadding = PaddingValues()
        ) {
            Box(modifier = Modifier
                .background(brush = gradient)
                .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center) {
                Text(text = "Button", color = Color.White)
            }
        }
    }
}
@Preview
@Composable
fun GradientBtnPreview(){
    GradientButton(gradient = Brush.horizontalGradient(
        colors = listOf(
            Color.Blue,
            Color.Magenta
        )
    ))
}