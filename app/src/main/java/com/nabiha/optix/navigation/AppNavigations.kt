package com.nabiha.optix.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cartfeatures.cartScreen.cartScreen
import com.example.wishlistfeatures.wishlistScreen.wishlistScreen
import com.nabiha.authfeatures.register.loginScreen
import com.nabiha.common.utils.NavRoute
import com.nabiha.designsystem.ui.BottomNavigationBar
import com.nabiha.homefeatures.detail.detailScreen
import com.nabiha.homefeatures.home.homeScreen
import com.example.appinfofeatures.appInfoScreen
import com.example.profilefeatures.profileScreen

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
            modifier = modifier.padding(bottom = max(innerPadding.calculateBottomPadding()- 18.dp, 0.dp))
        ) {
            homeScreen(navController)
            loginScreen(navController)
            detailScreen(navController, navController::popBackStack)
            wishlistScreen(navController)
            cartScreen(navController)
            appInfoScreen(navController, navController::popBackStack)
            profileScreen(navController)
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
        BottomBarNav.Profile
    )
    val routes = screens.map {
        it.route
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    AnimatedVisibility(visible = routes.any { it == currentDestination?.route }) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth().height(90.dp),
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