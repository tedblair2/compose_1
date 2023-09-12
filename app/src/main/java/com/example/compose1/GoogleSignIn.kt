package com.example.compose1

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GoogleSignInBtn(){
    var isClicked by remember { mutableStateOf(false) }
    
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(modifier = Modifier
            .padding(16.dp),
            shape = Shapes.medium,
            border = BorderStroke(1.dp,Color.LightGray),
            onClick = {
                isClicked =!isClicked
            }
        ) {
            Row(modifier = Modifier.padding(15.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription ="logo",
                tint = Color.Unspecified)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = if (isClicked) "Creating account..." else "Sign In to your Google Account",
                fontSize = 20.sp)
                Spacer(modifier = Modifier.width(6.dp))
                if (isClicked){
                    CircularProgressIndicator(modifier = Modifier.width(14.dp)
                        .height(14.dp),
                    color = Color.Blue,
                    strokeWidth = 2.dp)
                }
            }
        }
    }
}

@Preview
@Composable
fun GoogleSignInPreview(){
    GoogleSignInBtn()
}