package com.nabiha.homefeatures

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val homeRoute = NavRoute.HomeScreenRoute

fun NavController.navigateToHomeScreen() {
    navigate(homeRoute)
}

fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
) {
    composable(route = homeRoute) {
        HomeScreenRoute(navController)
    }
}