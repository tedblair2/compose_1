package com.example.compose1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation


@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImage(){
    val painter= rememberImagePainter(data = painterResource(id = R.drawable.b),
        builder = {
            placeholder(R.drawable.not_available)
            crossfade(1000)
            transformations(
                CircleCropTransformation()
            )
        })
    Box(modifier = Modifier
        .height(180.dp)
        .width(180.dp),
    contentAlignment = Alignment.Center) {
        Image(painter = painter, contentDescription ="image me")
    }
}

@Preview
@Composable
fun LoadImagePreview(){
    LoadImage()
}