package com.nabiha.historyfeatures.historyDetailScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nabiha.common.utils.NavRoute

const val HistoryDetailRoute = NavRoute.HistoryDetailScreenRute

fun NavGraphBuilder.historyDetailScreen(
    navController: NavHostController
) {
    composable(
        route = HistoryDetailRoute,
        arguments = listOf(navArgument("id_history") { type = NavType.LongType })
    ) {
        val idHistory = it.arguments?.getLong("id_history") ?: 0
        HistoryDetailScreenRoute(navController = navController, idHistory = idHistory)
    }
}