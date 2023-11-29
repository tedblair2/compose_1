package com.example.compose1

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteTransitionExample() {
    val infiniteTransition= rememberInfiniteTransition()
    val infiniteValues= List(3){index ->
        val delay= index * 100
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis=1200
                    0.0f at 0 with LinearOutSlowInEasing
                    1.0f at 300 with LinearOutSlowInEasing
                    0.0f at 600 with LinearOutSlowInEasing
                    0.0f at 1200 with LinearOutSlowInEasing
                },
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(offsetMillis = delay, offsetType = StartOffsetType.Delay)
            ), label = "load_anim"
        )
    }
    val distance= with(LocalDensity.current){
        20.dp.toPx()
    }
    Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        infiniteValues.forEachIndexed { index, state ->
            Box(modifier = Modifier
                .graphicsLayer {
                    translationY = -state.value * distance
                }
                .size(20.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colors.primary)) {}
            if (index != infiniteValues.size-1){
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}