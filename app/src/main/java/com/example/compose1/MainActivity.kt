package com.example.compose1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose1.canvas.CarSeatParent
import com.example.compose1.canvas.Seat
import com.example.compose1.search.AppViewModel
import com.example.compose1.stopwatch.StopWatchService
import com.example.compose1.ui.theme.Compose1Theme
import com.example.compose1.ui.theme.Typography


class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private val appViewModel:AppViewModel by viewModels()
    private var isBound by mutableStateOf(false)
    private lateinit var stopWatchService: StopWatchService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose1Theme {
                val context= LocalContext.current
                navHostController= rememberNavController()
//                NavGraphSetUp(navHostController = navHostController)
//                MainBottomNav(navHostController = navHostController)
//                MainSearchBar(appViewModel = appViewModel,context)
//                AnimatedShimmerEffect()
//                PagingNavGraph(navHostController = navHostController)
//                OnBoardingNavGraph(navHostController = navHostController)
//                MainOnBoardingScreen()
//                InfiniteTransitionExample()
//                HyperLinkText()
//                WebViewMainScreen()
//                SlidingAnimScreen()
//                ScrollDirection()
//                if (isBound){
//                    StopWatchScreen(stopWatchService = stopWatchService)
//                }
//                TextMarqueue()
//                TabLayout()
//                MainDrawerScreen(navHostController = navHostController, closeApp = { finish() })
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    BottomSheetExample()
//                }
//                NavDrawerNew(navHostController = navHostController, closeApp = { finish() })
                val bookedList= listOf(Seat("A8"),Seat("B18"))
                CarSeatParent(bookedList = bookedList)
            }
        }
    }
    private val serviceConnection=object :ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder=p1 as StopWatchService.ServiceBinder
            stopWatchService=binder.getService()
            isBound=true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound=false
        }

    }

    override fun onStart() {
        super.onStart()
        val intent=Intent(this,StopWatchService::class.java)
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
        isBound=false
    }
}

@Composable
fun CustomText(txt:String){
    Text(
        text = txt,
        style = Typography.h5)
}

@Composable
fun ColumnScope.ShowSurface(color:Color,weight:Float){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .weight(weight),
        color = color) {}
}

@Composable
fun BoxExample(){
    Box(modifier = Modifier
        .background(Color.Blue)
        .width(100.dp)
        .height(100.dp)) {

    }
}

@Composable
fun TextExample(){
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )){
                append("Ted Omino ")
            }
            append("Website is here and today is thursday so let's watch harley quin and nancy drew and grownish")
        }, modifier = Modifier.padding(30.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SuperScriptText(){
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 26.sp,
        )
        ){
            append("10")
        }
        withStyle(style = SpanStyle(
            fontSize = 14.sp,
            baselineShift = BaselineShift.Superscript,
            fontStyle = FontStyle.Italic
        )
        ){
            append("2")
        }
    }, modifier = Modifier.padding(15.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose1Theme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SuperScriptText()
        }
    }
}