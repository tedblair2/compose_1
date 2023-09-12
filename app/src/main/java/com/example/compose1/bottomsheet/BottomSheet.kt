package com.example.compose1.bottomsheet

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.compose1.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetExample() {
    val sheetState= rememberModalBottomSheetState(
        initialValue =ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true, animationSpec = tween(500, easing = LinearOutSlowInEasing)
    )
    val coroutineScope= rememberCoroutineScope()

    ModalBottomSheetLayout(sheetContent = {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(modifier = Modifier.weight(1f)){
                items(20){
                    Text(text = "Item $it")
                }
            }
            Button(onClick = { coroutineScope.launch { sheetState.hide() } }) {
                Text(text = "Hide me!")
            }
        }
    }, sheetState = sheetState, sheetGesturesEnabled = sheetState.isVisible, modifier = Modifier.fillMaxSize()) {
        Scaffold(floatingActionButton = {
            ExtendedFloatingActionButton(text = { Text(text = "Show Bottom Sheet") },
                onClick = { coroutineScope.launch { sheetState.show() } }, icon = { Icon(
                    painter = painterResource(id = R.drawable.baseline_visibility_24),
                    contentDescription = null,
                    tint = Color.Black
                )})
        }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { coroutineScope.launch { sheetState.show() } }) {
                    Text(text = "Show Bottom Sheet")
                }
            }
        }
    }
    
}