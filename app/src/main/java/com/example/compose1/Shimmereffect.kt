package com.example.compose1

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmerEffect() {
    val colors= listOf(
        Color.LightGray.copy(alpha = 0.7f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.7f)
    )
    val translate= rememberInfiniteTransition()
    val translateAnimation=translate.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush=Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(x=translateAnimation.value,y=translateAnimation.value)
    )
    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(brush: Brush) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        
        Spacer(modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(brush))
        Spacer(modifier = Modifier.width(8.dp))
        
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush))
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(20.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush))
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerPreview() {
    ShimmerItem(brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.7f),
                Color.LightGray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.7f)
            ),
    ))
}