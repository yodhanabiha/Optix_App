package com.nabiha.optix.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nabiha.common.utils.NavRoute
import com.nabiha.homefeatures.homeScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = NavRoute.HomeScreenRoute
){
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            homeScreen(navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarNav.Home,
        BottomBarNav.Wishlist,
        BottomBarNav.Virtual,
        BottomBarNav.Cart,
        BottomBarNav.Setting
    )
    val routes = screens.map {
        it.route
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(visible = routes.any { it == currentDestination?.route }) {
        NavigationBar(modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp), containerColor = Color.White) {
            screens.forEach { bottomNav ->
                AddItem(
                    bottomNav = bottomNav,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    bottomNav: BottomBarNav,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == bottomNav.route
        } == true,
        onClick = {
            navController.navigate(bottomNav.route)
        },
        label = {
            Text(text = bottomNav.title)
        },
        icon = {
            Icon(
                painter = painterResource(id = bottomNav.icon),
                contentDescription = "",
                modifier = Modifier.size(28.dp)
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = Color.White,
            unselectedIconColor = Color.Black,
            unselectedTextColor = Color.Black
        )
    )
}