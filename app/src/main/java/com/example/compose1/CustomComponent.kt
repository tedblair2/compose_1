package com.example.compose1

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomComponent(
    canvas:Dp=300.dp,
    indicatorValue:Int,
    maxIndicatorValue:Int=100,
    backgroundIndicatorColor: Color=MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth:Float=100f,
    foregroundIndicatorColor:Color=MaterialTheme.colors.primary,
    foregroundIndicatorStrokeWidth:Float=100f
){
    val percentage=((indicatorValue * 100f).div(maxIndicatorValue)).coerceIn(0f,100f)
    val sweepAngle by animateFloatAsState(
        targetValue = (2.4*percentage).toFloat(),
        animationSpec = tween(1500))
    val remainingValue by animateIntAsState(
        targetValue = (maxIndicatorValue.minus(indicatorValue)).coerceIn(0,100),
        animationSpec = tween(1500))
    val bigTextColor by animateColorAsState(
        targetValue = if (remainingValue==0) backgroundIndicatorColor else MaterialTheme.colors.onSurface,
        animationSpec = tween(1500))

    Column(modifier = Modifier
        .size(canvas)
        .drawBehind {
            val componentSize = size / 1.25f
            backgroundIndicator(
                componentSize,
                backgroundIndicatorColor,
                backgroundIndicatorStrokeWidth
            )
            foregroundIndicator(
                sweepAngle = sweepAngle,
                componentSize,
                foregroundIndicatorColor,
                foregroundIndicatorStrokeWidth
            )
        }, verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Remaining",
            fontSize = 17.sp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        Text(text = "$remainingValue GB",
            fontSize = 27.sp,
            color = bigTextColor,
            fontWeight = FontWeight.ExtraBold)
    }
}

fun DrawScope.backgroundIndicator(
    componentSize:Size,
    indicatorColor:Color,
    indicatorStrokeWidth:Float
){
    drawArc(
        size = componentSize,
        startAngle = 150f,
        sweepAngle = 240f,
        topLeft = Offset(
            x=(size.width - componentSize.width) / 2f,
            y=(size.height - componentSize.height) /2f
        ),
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        color = indicatorColor,
        useCenter = false
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle:Float,
    componentSize:Size,
    indicatorColor:Color,
    indicatorStrokeWidth:Float
){
    drawArc(
        size = componentSize,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        topLeft = Offset(
            x=(size.width - componentSize.width) / 2f,
            y=(size.height - componentSize.height) /2f
        ),
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        color = indicatorColor,
        useCenter = false
    )
}

@Composable
fun ShowCustom(){
    var value by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        CustomComponent(indicatorValue = value)
        TextField(
            value = value.toString(),
            onValueChange = {
                value = if (it.isNotEmpty()) it.toInt() else 0
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ))
    }
}
@Preview(showBackground = true)
@Composable
fun CustomPreview(){
    ShowCustom()
}