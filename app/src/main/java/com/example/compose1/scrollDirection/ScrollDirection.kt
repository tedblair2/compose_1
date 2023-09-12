package com.example.compose1.scrollDirection

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ScrollDirection() {
    val lazyListState= rememberLazyListState()
    Scaffold(content = {
        Box(modifier = Modifier.fillMaxSize()) {
            MainCollapseScreen(lazyListState = lazyListState)
            TopAppBarScroll(lazyListState = lazyListState)
        }
    })
}

@Composable
fun TopAppBarScroll(lazyListState: LazyListState) {
    TopAppBar(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary)
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
        .height(if (lazyListState.isScrollingUp()) 56.dp else 0.dp)) {
        
        Text(text = "Top App Bar", color = Color.White, fontSize = 25.sp)
    }
}

@Composable
fun MainCollapseScreen(lazyListState: LazyListState) {
    val padding=if (lazyListState.isScrollingUp()) 56.dp  else 0.dp
    LazyColumn(
        modifier = Modifier.padding(top = padding).fillMaxWidth().fillMaxHeight(),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        items(
            count = 50
        ){
            Text(text = "Item $it" ,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LazyListState.isScrollingUp():Boolean{
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}
