package com.example.compose1.tablayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(){
    val tabScreens= listOf(
        TabScreens.Home,
        TabScreens.Settings,
        TabScreens.Profile
    )
    val pagerState= rememberPagerState { tabScreens.size }
    val coroutineScope= rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabScreens.forEachIndexed { index, tabScreen ->
                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }, text = { Text(text = tabScreen.title) })
            }
        }
        HorizontalPager(state = pagerState) {page ->
            TabScreen(tabScreen = tabScreens[page])
        }
    }
}