package com.nabiha.optix.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nabiha.common.utils.NavRoute
import com.nabiha.designsystem.ui.BottomNavigationBar
import com.nabiha.homefeatures.homeScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoute.HomeScreenRoute
) {
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
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                screens.forEach { bottomNav ->
                    this@NavigationBar.AddItem(
                        bottomNav = bottomNav,
                        currentDestination = currentDestination,
                        navController = navController,
                    )
                }
            }

        }

    }


}


@Composable
fun RowScope.AddItem(
    bottomNav: BottomBarNav,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == bottomNav.route
    } == true

    BottomNavigationBar(
        selected = selected,
        navController = navController,
        title = bottomNav.title,
        route = bottomNav.route,
        iconSelected = bottomNav.icon,
        iconUnselected = bottomNav.iconNs,
    )

}