package com.nabiha.common.utils

import androidx.navigation.NavController

const val homeRoute = NavRoute.HomeScreenRoute
const val loginRoute = NavRoute.LoginScreenRoute
const val registerRoute = NavRoute.RegisterScreenRoute
const val appInfoRoute = NavRoute.AppInfoScreenRoute
const val cartRoute = NavRoute.CartScreenRoute
const val detailRoute = NavRoute.DetailScreenRoute
const val profileRoute = NavRoute.ProfileScreenRoute
const val WishlistRoute = NavRoute.WishlistScreenRoute

fun NavController.navigateToWishlistScreen() {
    navigate(WishlistRoute)
}

fun NavController.navigateToProfileScreen() {
    navigate(profileRoute)
}

fun NavController.navigateToDetailScreen() {
    navigate(detailRoute)
}
fun NavController.navigateToCartScreen() {
    navigate(cartRoute)
}
fun NavController.navigateToHomeScreen() {
    navigate(homeRoute)
}
fun NavController.navigateToLoginScreen(){
    navigate(loginRoute)
}
fun NavController.navigateToRegisterScreen(){
    navigate(registerRoute)
}
fun NavController.navigateToAppInfoScreen(){
    navigate(appInfoRoute)
}