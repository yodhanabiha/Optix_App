package com.nabiha.historyfeatures.historyScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val HistoryListRoute = NavRoute.HistoryScreenRute

fun NavGraphBuilder.historyListScreen(
    navController: NavHostController
) {
    composable(route = HistoryListRoute) {
        HistoryListScreenRoute(navController)
    }
}