package com.example.compose1.stopwatch.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.stopwatch.Constants
import com.example.compose1.stopwatch.ServiceHelper
import com.example.compose1.stopwatch.StopWatchService
import com.example.compose1.stopwatch.StopWatchState

@Composable
fun StopWatchScreen(stopWatchService: StopWatchService) {
    val context= LocalContext.current
    val hours by stopWatchService.hours
    val minutes by stopWatchService.minutes
    val seconds by stopWatchService.seconds
    val currentState by stopWatchService.currentState

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           AnimatedContent(targetState = hours, transitionSpec = { addAnimation() }) {
               Text(text = hours,
                   fontSize = 60.sp,
                   fontWeight = FontWeight.Bold,
                   color = if (hours=="00") Color.Black else Color.Blue )
           }
           AnimatedContent(targetState = minutes, transitionSpec = { addAnimation() }) {
               Text(text = minutes, fontSize = 60.sp,fontWeight = FontWeight.Bold,
                   color = if (minutes=="00") Color.Black else Color.Blue)
           }
            AnimatedContent(targetState = seconds, transitionSpec = { addAnimation() }) {
                Text(text = seconds, fontSize = 60.sp,fontWeight = FontWeight.Bold,
                    color = if (seconds=="00") Color.Black else Color.Blue)
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f), 
            verticalAlignment = Alignment.CenterVertically, 
            horizontalArrangement = Arrangement.Center) {
            
            Button(onClick = {
                ServiceHelper.triggerService(
                    context = context,
                    action = if (currentState==StopWatchState.STARTED) Constants.ACTION_SERVICE_STOP else Constants.ACTION_SERVICE_START)
            },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (currentState==StopWatchState.STARTED) Color.Red else Color.Blue,
                    contentColor = Color.White
                ) ) {
                Text(
                    text = when (currentState) {
                        StopWatchState.STARTED -> "Stop"
                        StopWatchState.STOPPED -> "Resume"
                        else -> "Start"
                    }
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = { ServiceHelper.triggerService(context,Constants.ACTION_SERVICE_RESET) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f),
                enabled = seconds != "00" && currentState == StopWatchState.STARTED,
                colors = ButtonDefaults.buttonColors( disabledBackgroundColor = Color.LightGray)) {
                Text(text = "Cancel")
            }
        }
    }
}

fun addAnimation(duration:Int=150): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)){ fullHeight -> fullHeight  } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) togetherWith  slideOutVertically(animationSpec = tween(durationMillis = duration)){ fullHeight -> -fullHeight  } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}
