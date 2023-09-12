package com.example.compose1.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navHostController: NavHostController) {
    var startAnim by remember { mutableStateOf(false) }
    val alphaVal by animateFloatAsState(
        targetValue =  if (startAnim) 1f else 0f,
        animationSpec = tween(3000)
    )
    LaunchedEffect(key1 = true){
        startAnim=true
        delay(4000)
        navHostController.popBackStack()
        navHostController.navigate(AUTH)
    }
    SplashScreenAnim(alpha = alphaVal)
}

@Composable
fun SplashScreenAnim(alpha:Float) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(if (isSystemInDarkTheme()) Color.Black.copy(alpha = ContentAlpha.medium) else MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center) {
        Icon(imageVector = Icons.Default.Email,
            contentDescription =null,
            tint = Color.White,
            modifier = Modifier.alpha(alpha)
                .size(70.dp))
    }
}

@Preview
@Composable
fun SplashPreview() {
    SplashScreenAnim(alpha = 1f)
}