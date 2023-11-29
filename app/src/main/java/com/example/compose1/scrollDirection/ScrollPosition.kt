package com.example.compose1.scrollDirection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun ScrollPosition() {
    val lazyListState= rememberLazyListState()
    val density= LocalDensity.current
    var previousIndex by remember {
        mutableIntStateOf(lazyListState.firstVisibleItemIndex)
    }
    var previousScrollOffset by remember {
        mutableIntStateOf(lazyListState.firstVisibleItemScrollOffset)
    }
    val currentPosition by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex }
    }
    val currentScrollOffset by remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset }
    }
    val heightPixels by remember {
        derivedStateOf {
            (lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0).plus(density.density*8)
        }
    }
    val totalItemsCount by remember {
        derivedStateOf { lazyListState.layoutInfo.totalItemsCount }
    }
    val visibleItemsCount by remember {
        derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.size }
    }
    LaunchedEffect(key1 = lazyListState.isScrollingDown()){
        previousIndex=lazyListState.firstVisibleItemIndex
        previousScrollOffset=lazyListState.firstVisibleItemScrollOffset
    }
    val isScrollDown=lazyListState.isScrollingDown()
    val itemCount by remember {
        derivedStateOf {
            val index= abs(previousIndex-currentPosition)
            index.minus(1).coerceAtLeast(0)
        }
    }
    var range by remember {
        mutableFloatStateOf(100f)
    }
    val progress by remember {
        derivedStateOf {
            (getTotalPixels(currentPosition,currentScrollOffset,heightPixels).coerceIn(0f,100f)).div(100)
        }
    }
    val pixelScrolled by remember {
        derivedStateOf {
            val pixels=if (previousIndex != currentPosition){
                (itemCount*heightPixels)+previousScrollOffset+(heightPixels-currentScrollOffset)
            }else{
                abs(previousScrollOffset-currentScrollOffset).toFloat()
            }.coerceIn(0f,100f)
            pixels
        }
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Scroll position $currentPosition")
        Text(text = "Scroll offset $currentScrollOffset")
        Text(text = "Height of item in pixels $heightPixels")
        Text(text = "Total item count $totalItemsCount")
        Text(text = "Visible item count $visibleItemsCount")
        Text(text = "Previous index $previousIndex")
        Text(text = "Previous scroll offset $previousScrollOffset")
        Text(text = "Items since previous $itemCount")
        Text(text = "Total Pixels scrolled $progress")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            items(count = 100){
                Box {
                    Text(text = "Item $it")
                }
            }
        }
    }
}

fun getTotalPixels(currentIndex:Int,currentOffset:Int,heightInPixels:Float):Float{
    return ((currentIndex*heightInPixels)+currentOffset).div(4)
}