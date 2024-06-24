package com.example.profilefeatures.editpassword

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nabiha.common.utils.NavRoute

const val EditPasswordRoute = NavRoute.EditPasswordScreenRute

fun NavGraphBuilder.editPasswordScreen(
    navController: NavHostController,
) {
    composable(route = EditPasswordRoute) {
        EditPasswordScreenRoute(navController)
    }
}