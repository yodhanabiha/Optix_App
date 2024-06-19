package com.example.profilefeatures.edit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nabiha.common.utils.NavRoute
import com.nabiha.entity.UserEntity

const val eProfileRoute = NavRoute.EditProfileScreenRoute

fun NavGraphBuilder.editProfileScreen(
    navController: NavHostController,
) {
    composable(
        route = "${eProfileRoute}?user={user}",
        arguments = listOf(navArgument("user") { type = NavType.StringType })
    ) {
        val userJson = it.arguments?.getString("user")
        val user = Gson().fromJson(userJson, UserEntity::class.java)
        EditProfileScreenRoute(navController, user)
    }
}
