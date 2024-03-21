package com.nabiha.authfeatures.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val registerRoute = NavRoute.RegisterScreenRoute

fun NavController.navigateToRegisterScreen(){
    navigate(registerRoute)
}

fun NavGraphBuilder.loginScreen(
    navController: NavHostController,
) {
    composable(route = registerRoute) {
        RegisterScreenRoute(navController = navController)
    }
}
