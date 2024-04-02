package com.example.wishlistfeatures.wishlistScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val WishlistRoute = NavRoute.WishlistScreenRoute

fun NavController.navigateToWishlistScreen() {
    navigate(WishlistRoute)
}

fun NavGraphBuilder.wishlistScreen(
    navController: NavHostController
) {
    composable(route = WishlistRoute) {
        WishlistScreenRoute(navController)
    }
}