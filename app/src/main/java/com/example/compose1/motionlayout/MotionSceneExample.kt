package com.example.compose1.motionlayout

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.Transition
import com.example.compose1.R
import com.example.compose1.scrollDirection.getTotalPixels

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionSceneExample() {
    val density= LocalDensity.current
    val context= LocalContext.current
    val lazyListState= rememberLazyListState()
    val headerTransition = remember {
        context.resources.openRawResource(R.raw.header_transition).readBytes().decodeToString()
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
    val progress by remember {
        derivedStateOf {
            (getTotalPixels(currentPosition,currentScrollOffset,heightPixels).coerceIn(0f,100f)).div(100)
        }
    }
    val progressAnim by animateFloatAsState(
        targetValue =progress,
        label = "progress",
        animationSpec = tween(500))

    val imageBorder by animateColorAsState(
        targetValue = if (progress in 0.0..0.7) Color.White else Color.Red,
        label = "border",
        animationSpec = tween(600)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        MotionLayout(
            start = endConstraints(),
            end = startConstraints(),
            progress =progressAnim,
            transition = Transition(content = headerTransition),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .layoutId("header"))
            Box(modifier = Modifier
                .fillMaxWidth()
                .layoutId("list")){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(8.dp)){
                    items(count = 100){
                        Box {
                            Text(text = "Item $it")
                        }
                    }
                }
            }
            Image(painter = painterResource(id = R.drawable.b),
                contentDescription =null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = imageBorder,
                        shape = CircleShape
                    )
                    .layoutId("profileImage"))

            Text(text = "Ted Blair Omino",
                color = Color.White,
                modifier = Modifier
                    .layoutId("username"),
                fontSize = 19.sp)

        }
    }
}
