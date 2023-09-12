package com.example.compose1.bottomNav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomNav(navHostController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navHostController = navHostController)
        }
    ) {
        BottomNavGraph(navHostController = navHostController)
    }
}

@Composable
fun BottomNavBar(navHostController: NavHostController) {
    val screens= listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Settings,
        BottomNavScreen.Profile
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination

    BottomNavigation() {
        screens.forEach {
            AddItem(screen = it, navHostController =navHostController , currentDestination =currentDestination)
        }
    }
}

@Composable
fun RowScope.AddItem(screen: BottomNavScreen,navHostController: NavHostController,currentDestination: NavDestination?) {
    BottomNavigationItem(
        label = { Text(text = screen.title)},
        selected = currentDestination?.hierarchy?.any { it.route == screen.route }==true,
        icon = { Icon(imageVector = screen.icon, contentDescription ="nav icon" )},
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(screen.route){
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop=true
            }
        })

}
