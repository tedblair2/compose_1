@file:OptIn(ExperimentalFoundationApi::class)

package com.example.compose1.marqueue

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextMarqueue() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello there e and n notn s cup is now in the house come down to the" +
                " coming home to fish and ugali cup water lorem ipsim oren mno cash",
            fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth()
            .basicMarquee(
                iterations = Int.MAX_VALUE,
                velocity = 50.dp,
                delayMillis = 0,
                initialDelayMillis = 0
            ))
    }
}