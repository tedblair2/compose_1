package com.example.compose1.navigationDrawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch

@Composable
fun MainDrawerScreen(navHostController: NavHostController,closeApp:()->Unit) {
    val scaffoldState= rememberScaffoldState()
    val coroutineScope= rememberCoroutineScope()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
    val firstScreen=currentDestination?.hierarchy?.any { it.route == DrawerScreen.Home.route} ?: false

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Navigation Drawer")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerItems(onItemClicked = {drawerScreen->
                navHostController.navigate(drawerScreen.route){
                    popUpTo(navHostController.graph.findStartDestination().id)
                    launchSingleTop=true
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }, isItemSelected = {drawerScreen ->
                currentDestination?.hierarchy?.any { it.route == drawerScreen.route } ?: false
            })
        }
    ) {
        DrawerNavGraph(navHostController = navHostController, onBackPressed = {
            if (scaffoldState.drawerState.isOpen){
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }else if (firstScreen){
                closeApp()
            }else{
                navHostController.popBackStack()
            }
        })
    }
}

@Composable
fun DrawerItems(onItemClicked:(DrawerScreen)->Unit,isItemSelected:(DrawerScreen)->Boolean) {
    val drawerScreens= listOf(
        DrawerScreen.Home,
        DrawerScreen.Settings,
        DrawerScreen.Profile
    )
    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp)){
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
                contentAlignment = Alignment.Center){
                Text(text = "Nav Title", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Divider()
        }
        items(items = drawerScreens){drawerScreen->
            DrawerItem(drawerScreen = drawerScreen,isItemSelected(drawerScreen), onItemClicked={onItemClicked(drawerScreen)})
        }
    }
}

@Composable
fun DrawerItem(drawerScreen: DrawerScreen,isSelected:Boolean,onItemClicked:()->Unit) {
    val color=if (isSelected) Color.LightGray.copy(alpha = ContentAlpha.medium) else Color.White
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(start = 6.dp, end = 6.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color)
        .clickable { onItemClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp) ) {
        Icon(imageVector = drawerScreen.imageVector,
            contentDescription =null, modifier =Modifier.padding(start = 10.dp))
        Text(text = drawerScreen.title, fontSize = 20.sp, fontWeight = FontWeight.Normal)
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
//    DrawerItems(onItemClicked = {},null)
}