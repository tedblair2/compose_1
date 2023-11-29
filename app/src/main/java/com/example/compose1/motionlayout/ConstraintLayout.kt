package com.example.compose1.motionlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.compose1.R

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize(), constraintSet = endConstraints()) {
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
                    color = Color.White,
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

fun startConstraints():ConstraintSet{
    return ConstraintSet {
        val header=createRefFor("header")
        val list=createRefFor("list")
        val profileImage=createRefFor("profileImage")
        val username=createRefFor("username")

        constrain(header){
            width= Dimension.matchParent
            height=Dimension.value(56.dp)
            top.linkTo(parent.top)
        }
        constrain(list){
            width=Dimension.matchParent
            height= Dimension.fillToConstraints
            top.linkTo(header.bottom,0.dp)
            bottom.linkTo(parent.bottom,0.dp)
        }
        constrain(profileImage){
            width=Dimension.value(40.dp)
            height=Dimension.value(40.dp)
            start.linkTo(parent.start,16.dp)
            top.linkTo(parent.top,8.dp)
        }
        constrain(username){
            top.linkTo(profileImage.top)
            bottom.linkTo(profileImage.bottom)
            start.linkTo(profileImage.end,10.dp)
        }
    }
}

fun endConstraints():ConstraintSet{
    return ConstraintSet {
        val header=createRefFor("header")
        val list=createRefFor("list")
        val profileImage=createRefFor("profileImage")
        val username=createRefFor("username")

        constrain(header){
            width=Dimension.matchParent
            height= Dimension.value(200.dp)
            top.linkTo(parent.top)
        }
        constrain(list){
            width= Dimension.matchParent
            height= Dimension.fillToConstraints
            top.linkTo(header.bottom,0.dp)
            bottom.linkTo(parent.bottom,0.dp)
        }
        constrain(profileImage){
            width= Dimension.value(130.dp)
            height= Dimension.value(130.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top,15.dp)
        }
        constrain(username){
            top.linkTo(profileImage.bottom,14.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}