package com.example.compose1

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularImage() {
    Image(
        painter = painterResource(id = R.drawable.b),
        contentDescription ="person",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape),
        contentScale = ContentScale.Crop)
}

@Preview(showBackground = true)
@Composable
fun CircularImagePreview() {
    CircularImage()
}