package com.example.compose1.navigationDrawer

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch

@Composable
fun NavDrawerNew(navHostController: NavHostController,closeApp:()->Unit) {
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope= rememberCoroutineScope()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
    val firstScreen=currentDestination?.hierarchy?.any { it.route == DrawerScreen.Home.route} ?: false

    ModalDrawer(drawerContent = {
        DrawerItems(onItemClicked = {drawerScreen->
        navHostController.navigate(drawerScreen.route){
            popUpTo(navHostController.graph.findStartDestination().id)
            launchSingleTop=true
        }
        scope.launch {
            drawerState.close()
        }
    }, isItemSelected = {drawerScreen ->
        currentDestination?.hierarchy?.any { it.route == drawerScreen.route } ?: false })},
        drawerState=drawerState) {

        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = "Navigation Drawer")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                }
            )
        }) {
            DrawerNavGraph(navHostController = navHostController, onBackPressed = {
                if (drawerState.isOpen){
                    scope.launch {
                        drawerState.close()
                    }
                }else if (firstScreen){
                    closeApp()
                }else{
                    navHostController.popBackStack()
                }
            })
        }
    }
}