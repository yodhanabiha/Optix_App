package com.example.cartfeatures.cartScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val cartRoute = NavRoute.CartScreenRoute

fun NavGraphBuilder.cartScreen(
    navController: NavHostController,
) {
    composable(route = cartRoute) {
        CartScreenRoute(navController)
    }
}