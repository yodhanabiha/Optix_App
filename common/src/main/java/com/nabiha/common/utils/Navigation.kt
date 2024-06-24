package com.nabiha.common.utils

import android.net.Uri
import androidx.navigation.NavController
import com.google.gson.Gson
import com.nabiha.entity.UserEntity

const val homeRoute = NavRoute.HomeScreenRoute
const val loginRoute = NavRoute.LoginScreenRoute
const val registerRoute = NavRoute.RegisterScreenRoute
const val appInfoRoute = NavRoute.AppInfoScreenRoute
const val cartRoute = NavRoute.CartScreenRoute
const val detailRoute = NavRoute.DetailScreenRoute
const val detailHistoryRoute = NavRoute.HistoryDetailScreenRute
const val historyRoute = NavRoute.HistoryScreenRute
const val profileRoute = NavRoute.ProfileScreenRoute
const val WishlistRoute = NavRoute.WishlistScreenRoute
const val editProfileRoute = NavRoute.EditProfileScreenRoute

fun NavController.navigateToWishlistScreen() {
    navigate(WishlistRoute)
}

fun NavController.navigateToProfileScreen() {
    navigate(profileRoute)
}

fun NavController.navigateToEditPasswordScreen() {
    navigate(NavRoute.EditPasswordScreenRute)
}

fun NavController.navigateToEditProfileScreen(user: UserEntity) {
    val route = editProfileRoute + "?user=${Uri.encode(Gson().toJson(user))}"
    navigate(route)
}

fun NavController.navigateToDetailScreen(idProduct: Long) {
    navigate("detailScreenRoute/$idProduct")
}

fun NavController.navigateToDetailHistoryScreen(idHistory: Long) {
    navigate("historyDetailScreenRoute/$idHistory")
}

fun NavController.navigateToHistoryScreen() {
    navigate(historyRoute)
}

fun NavController.navigateToCartScreen() {
    navigate(cartRoute)
}

fun NavController.navigateToHomeScreen() {
    navigate(homeRoute)
}

fun NavController.navigateToLoginScreen() {
    navigate(loginRoute)
}

fun NavController.navigateToRegisterScreen() {
    navigate(registerRoute)
}

fun NavController.navigateToAppInfoScreen() {
    navigate(appInfoRoute)
}