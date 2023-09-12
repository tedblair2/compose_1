package com.example.compose1.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainOnBoardingScreen() {
    val onBoardPages= listOf(
        OnBoardingScreens.Email,
        OnBoardingScreens.Settings,
        OnBoardingScreens.Person
    )
    val pagerState = rememberPagerState { onBoardPages.size }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalPager(state = pagerState) {page ->
            PagerContent(onBoardingScreens = onBoardPages[page])
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center) {
            repeat(onBoardPages.size){position->
                val color=if (pagerState.currentPage==position) Color.DarkGray else Color.LightGray
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .background(color = color)
                    .size(8.dp)) {
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        AnimatedVisibility(visible = pagerState.currentPage == onBoardPages.size-1) {
            Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(10.dp,5.dp)) {
                Text(text = "Go to Next Page")
            }
        }
    }
}

@Composable
fun PagerContent(onBoardingScreens: OnBoardingScreens) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Center, 
        horizontalAlignment = Alignment.CenterHorizontally) {
        
        Icon(
            imageVector = onBoardingScreens.image, 
            contentDescription =null, 
            tint = onBoardingScreens.tint, modifier = Modifier
                .size(100.dp)
                .padding(10.dp))
        
        Text(text = onBoardingScreens.title, fontSize = 25.sp, modifier = Modifier.padding(10.dp))
        Text(text = onBoardingScreens.subtitle, fontSize = 18.sp, modifier = Modifier.padding(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PagerPreview() {
    PagerContent(onBoardingScreens =OnBoardingScreens.Email)
}