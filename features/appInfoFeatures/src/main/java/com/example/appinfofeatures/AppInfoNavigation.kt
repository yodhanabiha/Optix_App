package com.example.appinfofeatures

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val appInfoRoute = NavRoute.AppInfoScreenRoute

fun NavGraphBuilder.appInfoScreen(
    navController: NavHostController,
    onBackBtnClick:()->Unit
) {
    composable(route = appInfoRoute) {
        AppInfoScreenRoute(navController, onBackBtnClick)
    }
}
