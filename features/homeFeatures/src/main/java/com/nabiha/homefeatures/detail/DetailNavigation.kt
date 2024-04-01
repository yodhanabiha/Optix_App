package com.nabiha.homefeatures.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val detailRoute = NavRoute.DetailScreenRoute

fun NavController.navigateToDetailScreen() {
    navigate(detailRoute)
}

fun NavGraphBuilder.detailScreen(
    navController: NavHostController,
    onBackBtnClick:()->Unit
) {
    composable(route = detailRoute) {
        DetailScreenRoute(navController, onBackBtnClick)
    }
}
