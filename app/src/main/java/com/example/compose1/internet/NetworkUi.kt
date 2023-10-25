package com.example.compose1.internet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun NetworkUi(viewModel: NetworkViewModel= koinViewModel()) {
    val networkStatus by viewModel.currentNetworkStatus.collectAsState()
    val text=when(networkStatus){
        is NetworkStatus.Connected->"Network connected!"
        else->"Network not connected!!"
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = text, fontSize = 28.sp)
    }
}