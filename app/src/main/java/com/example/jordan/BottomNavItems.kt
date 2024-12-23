package com.example.jordan

import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import okhttp3.internal.wait

enum class BottomNavItems(
    val route: String,
    val title: String,
    val icon: Int,

) {

    HOME(
        route = "home", title = "home",
        icon = R.drawable.bottomnav_home
    ),

    Favourites(
        route = "favourites", title = "favourites",
        icon = R.drawable.bottomnav_heart
    ),

    NULLS(route = "", title = "", icon = 0),

    Notifications(
        route = "notifications", title = "notifications",
        icon = R.drawable.bottomnav_notification
    ),

    PROFILE(
        route = "profile", title = "profile",
        icon = R.drawable.bottomnav_profile
    ),


}