package com.example.jordan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun ScreensNavController(){//navController: NavController){

    val withPx = LocalContext.current.resources.displayMetrics.widthPixels

    val barShape = BarShape(
        offset = withPx / 2f,
        circleRadius = 70.dp,
        circleGap = 0.dp,
    )

    val navItemList = listOf(
        NavItem(icon = ImageVector.vectorResource(id = R.drawable.bottomnav_home)),
        NavItem(icon = ImageVector.vectorResource(id = R.drawable.bottomnav_heart)),
        NavItem(icon = null),
        NavItem(icon = ImageVector.vectorResource(id = R.drawable.bottomnav_notification)),
        NavItem(icon = ImageVector.vectorResource(id = R.drawable.bottomnav_profile)),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white),
                    contentColor = colorResource(id = R.color.white)
                ),
                shape = barShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
                modifier = Modifier
                    .background(shape = barShape, color = colorResource(id = R.color.white))
                    .height(106.dp)
                    .shadow(
                        elevation = 100.dp,
                        shape = barShape,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    )
            ) {

                NavigationBar(
                    containerColor = colorResource(id = R.color.white),
                    modifier = Modifier
                        .background(Color.Red, shape = barShape)
                        .height(106.dp)

                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            enabled = navItem.icon != null,
                            icon = {
                                if (navItem.icon != null)
                                    Icon(
                                        imageVector = navItem.icon,
                                        contentDescription = "?",
                                        tint = if (selectedIndex == index) colorResource(id = R.color.bottomnav_icon_selected)
                                        else colorResource(id = R.color.bottomnav_icon_unselected)
                                    )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = colorResource(id = R.color.bottomnav_icon_selected),
                                unselectedIconColor = colorResource(id = R.color.bottomnav_icon_unselected),
                            ),
                            modifier = Modifier
                                .padding(top = 30.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}





@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int) {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white2)),
    ) {


        when(selectedIndex){
            0 -> { Home() }
            1 -> {}
            2 -> {}
            3 -> {}
        }


        FloatingActionButton(
            onClick = {  },
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.bottomnav_icon_selected),
            shape = RoundedCornerShape(30.dp),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp,
            ),

            modifier = Modifier
                .size(56.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-50).dp)
                .shadow(
                    elevation = 8.dp, spotColor = Color(91, 158, 255, (255 / 100) * 60),
                    ambientColor = Color(91, 158, 255, (255 / 100) * 60)
                )
                //.padding(bottom = 50.dp)
        ) {
            Icon(
                painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_fab)),
                contentDescription = "Cart",
                tint = colorResource(id = R.color.white)
            )
        }
    }
}



















/*fun createCustomBottomBarPath(width: Float, height: Float, curveRadius: Float): Path {
    return Path().apply {
        moveTo(0f, 0f)
        lineTo(0f, height)
        lineTo(width, height)
        lineTo(width, 0f)

        // Curve in the middle
        val curveCenter = width / 2
        cubicTo(
            curveCenter - curveRadius * 1.5f, 0f,
            curveCenter - curveRadius, height * 0.6f,
            curveCenter, height * 0.6f
        )
        cubicTo(
            curveCenter + curveRadius, height * 0.6f,
            curveCenter + curveRadius * 1.5f, 0f,
            width, 0f
        )
    }
}*/
