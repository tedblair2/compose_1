package com.example.compose1.motionlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.compose1.R
import com.example.compose1.model.SwipingStates

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterialApi::class)
@Composable
fun MotionLayoutExample(modifier: Modifier=Modifier) {
    val context= LocalContext.current
    val motionScene= remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }
    val swipingState= rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val heightInPx= with(LocalDensity.current){maxHeight.toPx()}

        val nestedScrollConnection= remember {
            object :NestedScrollConnection{
                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity,
                ): Velocity {
                    swipingState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset {
                    val delta=available.y
                    return swipingState.performDrag(delta).toOffset()
                }

                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta=available.y
                    return if (delta<0){
                        swipingState.performDrag(delta).toOffset()
                    }else{
                        Offset.Zero
                    }
                }

            }
        }
        
        Box(modifier = Modifier
            .fillMaxSize()
            .swipeable(
                state = swipingState,
                orientation = Orientation.Vertical,
                thresholds = { _, _ ->
                    FractionalThreshold(0.05f)
                },
                anchors = mapOf(
                    0f to SwipingStates.COLLAPSED,
                    heightInPx to SwipingStates.EXPANDED
                )
            )
            .nestedScroll(nestedScrollConnection)){

            val progress by remember {
                derivedStateOf {
                    if (swipingState.progress.to == SwipingStates.COLLAPSED){
                        swipingState.progress.fraction
                    }else{
                        1f-swipingState.progress.fraction
                    }
                }
            }
            MotionLayout(
                motionScene = MotionScene(content = motionScene),
                progress = progress,
                modifier = modifier.fillMaxSize()) {

                val imageProperties=motionProperties(id = "profile_pic")

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray)
                    .layoutId("box"))

                Image(painter = painterResource(id = R.drawable.b),
                    contentDescription =null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = imageProperties.value.color("background"),
                            shape = CircleShape
                        )
                        .layoutId("profile_pic"))

                Text(text = "Ted Blair Omino",
                    color = Color.White,
                    modifier = Modifier
                        .layoutId("username"),
                    fontSize = 20.sp)

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("body")){

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
            }
        }
    }

}

fun Float.toOffset()= Offset(0f,this)

fun startConstraint():ConstraintSet{
    return ConstraintSet {
        val box=createRefFor("box")
        val profile_pic=createRefFor("profile_pic")
        val username=createRefFor("username")
        val body=createRefFor("body")

        constrain(box){
            width= Dimension.matchParent
            height=Dimension.value(200.dp)
        }
        constrain(body){
            width= Dimension.matchParent
            height= Dimension.fillToConstraints
            top.linkTo(box.bottom,0.dp)
            bottom.linkTo(parent.bottom,0.dp)
        }
        constrain(profile_pic){
            height=Dimension.value(150.dp)
            width=Dimension.value(150.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top,16.dp)
        }
        constrain(username){
            top.linkTo(profile_pic.bottom,16.dp)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }
    }
}

fun endConstraint():ConstraintSet{
    return ConstraintSet {
        val box=createRefFor("box")
        val profile_pic=createRefFor("profile_pic")
        val username=createRefFor("username")
        val body=createRefFor("body")

        constrain(box){
            width= Dimension.matchParent
            height=Dimension.value(56.dp)
        }
        constrain(body){
            width= Dimension.matchParent
            height= Dimension.fillToConstraints
            top.linkTo(box.bottom,0.dp)
            bottom.linkTo(parent.bottom,0.dp)
        }
        constrain(profile_pic){
            height=Dimension.value(40.dp)
            width=Dimension.value(40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(box.top,8.dp)
            bottom.linkTo(box.top,8.dp)
        }
        constrain(username){
            top.linkTo(profile_pic.top)
            start.linkTo(profile_pic.end,10.dp)
            bottom.linkTo(profile_pic.bottom)
        }
    }
}