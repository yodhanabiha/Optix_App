package com.nabiha.optix.navigation

import com.nabiha.common.utils.NavRoute
import com.nabiha.optix.R

sealed class BottomBarNav(
    val route: String,
    val title: String,
    val icon : Int,
    val iconNs : Int
) {
    object Home :BottomBarNav(
        route = NavRoute.HomeScreenRoute,
        title = "Home",
        icon = R.drawable.home_icn,
        iconNs = R.drawable.home_icn_ns
    )
    object Wishlist :BottomBarNav(
        route = "wishlist",
        title = "Wishlist",
        icon = R.drawable.love_icn,
        iconNs = R.drawable.love_icn_ns
    )
    object Virtual :BottomBarNav(
        route = "virtual",
        title = "Virtual\n" + "Try-On",
        icon = R.drawable.scan_icn,
        iconNs = R.drawable.scan_icn
    )
    object Cart: BottomBarNav(
        route = "cart",
        title = "Cart",
        icon = R.drawable.trolley_icn,
        iconNs = R.drawable.trolley_icn_ns
    )

    object Setting: BottomBarNav(
        route = "setting",
        title = "Setting",
        icon = R.drawable.setting_icn,
        iconNs = R.drawable.setting_icn_ns
    )

}