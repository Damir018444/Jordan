package com.example.jordan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController

@Composable
fun RowScope.AddItem (
    route: String,
    title: String,
    icon: Int,
    //iconFocused: Int,
    navDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = navDestination?.hierarchy?.any { it.route == route } == true

    NavigationBarItem (
        icon = {
            Icon (
                painter = painterResource(id = icon),
                contentDescription = "?",
                modifier = Modifier.size(24.dp),
                tint = if (selected) colorResource(id = R.color.bottomnav_icon_selected) else colorResource(id = R.color.bottomnav_icon_unselected),
            )
        },


        selected = selected,
        onClick = {
            navController.navigate(route) {
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = colorResource(id = R.color.bottomnav_icon_selected),
            unselectedIconColor = colorResource(id = R.color.bottomnav_icon_unselected),
            //indicatorColor = CustomColors.white_ffffff,
        ),
    )
}