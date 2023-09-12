package com.example.compose1.slidingAnim

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun SlidingAnimScreen(viewModel: SlidingAnimViewModel= koinViewModel()) {

    val seconds=viewModel.seconds.collectAsState(initial = "00")

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        AnimatedContent(
            targetState = seconds.value, transitionSpec = {
                addAnimation().using(SizeTransform(clip = true))
            }) {targetState ->
            Text(text = "${targetState}", fontSize = 65.sp, textAlign = TextAlign.Center)
        }
    }
}

fun addAnimation(duration:Int=800):ContentTransform{
    return slideInHorizontally(animationSpec = tween(durationMillis = duration)){ fullHeight -> -fullHeight  } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) togetherWith  slideOutHorizontally(animationSpec = tween(durationMillis = duration)){ fullHeight -> fullHeight  } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}