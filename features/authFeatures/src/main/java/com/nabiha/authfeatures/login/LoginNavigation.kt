package com.nabiha.authfeatures.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val loginRoute = NavRoute.LoginScreenRoute
fun NavGraphBuilder.loginScreen(
    navController: NavHostController,
) {
    composable(route = loginRoute) {
        LoginScreenRoute(navController = navController)
    }
}
