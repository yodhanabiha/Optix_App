package com.example.appinfofeatures

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val appInfoRoute = NavRoute.AppInfoScreenRoute

fun NavController.navigateToAppInfoScreen(){
    navigate(appInfoRoute)
}

fun NavGraphBuilder.appInfoScreen(
    navController: NavHostController,
) {
    composable(route = appInfoRoute) {
        AppInfoScreenRoute(navController = navController)
    }
}
