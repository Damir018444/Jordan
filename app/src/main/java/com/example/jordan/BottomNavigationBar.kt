package com.example.jordan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.ktor.util.sha1

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val withPx = LocalContext.current.resources.displayMetrics.widthPixels

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val barShape = BarShape2(
        offset = withPx / 2f,
        circleRadius = 30.dp,
        cornerRadius = 30.dp,
        circleGap = 15.dp,
    )

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Gray
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
        shape = barShape,
        modifier = Modifier
            .background(shape = barShape, color = colorResource(id = R.color.white))
            .shadow(
                elevation = 30.dp,
                shape = barShape
            )
    ) {
        NavigationBar (
            modifier = Modifier
                .background(color = colorResource(id = R.color.white))
                .fillMaxWidth()
                .height(80.dp)
                .graphicsLayer(shape = barShape, clip = true),

            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.white)
        ) {

            BottomNavItems.entries.forEach { screen ->

                if (screen.route == "") {
                    Spacer(
                        modifier = Modifier
                            .width(50.dp)
                            .fillMaxHeight()
                    )

                } else {
                    AddItem(
                        route = screen.route,
                        title = screen.title,
                        icon = screen.icon,
                        navDestination = currentDestination,
                        navController = navHostController
                    )
                }
            }
        }
    }
}