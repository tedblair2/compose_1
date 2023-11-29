package com.example.compose1.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun DraggableContent() {
    var offsetX by remember {
        mutableFloatStateOf(0f)
    }
    val draggableState= rememberDraggableState(onDelta = {delta->
        offsetX +=delta
    })

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = Modifier
            .size(60.dp)
            .padding(8.dp)
            .graphicsLayer {
                translationX = offsetX
            }
            .draggable(
                state = draggableState,
                orientation = Orientation.Horizontal
            )
            .background(Color.Red))

        Text(text = "$offsetX", fontSize = 25.sp)
    }
}

@Composable
fun VerticalAndHorizontalDrag() {
    var offsetY by remember {
        mutableFloatStateOf(0f)
    }
    var offsetX by remember {
        mutableFloatStateOf(0f)
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier = Modifier
            .size(60.dp)
            .padding(10.dp)
            .offset {
                IntOffset(x = offsetX.roundToInt(), y = offsetY.roundToInt())
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .background(Color.Blue))
        
        Text(text = "Offset X $offsetX \nOffset Y $offsetY", fontSize = 20.sp)
    }
}

@Composable
fun VerticalAndHorizontalLongPressDrag() {
    var offsetY by remember {
        mutableFloatStateOf(0f)
    }
    var offsetX by remember {
        mutableFloatStateOf(0f)
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {

        Box(modifier = Modifier
            .size(60.dp)
            .padding(10.dp)
            .offset {
                IntOffset(x = offsetX.roundToInt(), y = offsetY.roundToInt())
            }
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .background(Color.Blue))

        Text(text = "Offset X $offsetX \nOffset Y $offsetY",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)
    }
}