package com.sattar.mybottomnavigation.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    //get our navController for the app
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            //create our bottom bar with navigation
            BottomBar(navController = navController)
        }
    ) {
        //create our nav graph we implemented with all screen in bottom bar
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    //Create our screens to pass it to our bottom navigation.
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Notifications,
    )
    //observe whenever our back stack changed
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //Create our composable BottomNavigation already predefined function comes with mateial lib
    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    // for each screen we are calling this to add Navigation item to BottomNavigation
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        // checking if this item is the  current Destination to mark it selected
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        //make unselected item as faded
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            //make the navController navgiate to our screen as we are using BottomNavGraph
            navController.navigate(screen.route) {
                // we are popup current destination so when click back button it will always go to home
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

