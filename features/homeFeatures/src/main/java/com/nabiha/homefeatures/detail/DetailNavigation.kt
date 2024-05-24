package com.nabiha.homefeatures.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nabiha.common.utils.NavRoute

const val detailRoute = NavRoute.DetailScreenRoute

fun NavGraphBuilder.detailScreen(
    navController: NavHostController,
    onBackBtnClick: () -> Unit
) {
    composable(
        route = detailRoute,
        arguments = listOf(navArgument("id_product") { type = NavType.LongType })
    ) {
        val idProduct = it.arguments?.getLong("id_product") ?: 0
        DetailScreenRoute(navController, idProduct, onBackBtnClick = onBackBtnClick)
    }
}
