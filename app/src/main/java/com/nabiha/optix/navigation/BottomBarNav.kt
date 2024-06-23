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
        route = NavRoute.WishlistScreenRoute,
        title = "Wishlist",
        icon = R.drawable.love_icn,
        iconNs = R.drawable.love_icn_ns
    )

    object Cart: BottomBarNav(
        route = NavRoute.HistoryScreenRute,
        title = "History",
        icon = com.nabiha.designsystem.R.drawable.file_text_fill,
        iconNs = com.nabiha.designsystem.R.drawable.file_text
    )

    object Profile: BottomBarNav(
        route = NavRoute.ProfileScreenRoute,
        title = "Profile",
        icon = R.drawable.person_fill,
        iconNs = R.drawable.person_icn_ns
    )

}

