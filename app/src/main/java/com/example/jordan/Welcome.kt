package com.example.jordan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignIn()
        }
    }
}



@Composable
fun Start(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable(
            "first",
            enterTransition = { fadeIn(animationSpec = tween(2000)) },
            exitTransition = { fadeOut(animationSpec = tween(2000)) }
        ) { FirstScreen()}//navController) }
        composable(
            "second",
            enterTransition = { fadeIn(animationSpec = tween(2000)) },
            exitTransition = { fadeOut(animationSpec = tween(2000)) }
            ) { SecondScreen()}//navController) }
        composable(
            "third",
            enterTransition = { fadeIn(animationSpec = tween(2000)) },
            exitTransition = { fadeOut(animationSpec = tween(2000)) }
        ) { ThirdScreen() }
    }
}





@Composable
fun SplashScreen(navController: NavController){
    Box (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.gradient1),
                        colorResource(id = R.color.gradient2)
                    )
                )
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image (
            painter = painterResource(id = R.drawable.app_title),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .fillMaxHeight(0.1F)
                .offset(y = (-45).dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("first")
    }
}








//@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun FirstScreen(){//navController: NavController){
    Box (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.gradient1),
                        colorResource(id = R.color.gradient2)
                    )
                )
            )
            .fillMaxSize(),
    ) {

        Image (
            painter = painterResource(id = R.drawable.welcome1_text),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(267.dp, 70.dp)
                .align(Alignment.TopCenter)
                .offset(y = 121.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.zakoruchka4),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(27.dp, 30.dp)
                .offset(59.dp, 102.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.zakoruchka3),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(134.dp, 18.dp)
                .align(Alignment.TopCenter)
                .offset(y = 210.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_boot1),
            contentScale = ContentScale.FillWidth,
            contentDescription = "App title",
            modifier = Modifier
                .size(671.37.dp, 588.05.dp)
                .align(Alignment.TopCenter)
                .offset(y = 102.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_indicator1),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(118.dp, 5.dp)
                .align(Alignment.TopCenter)
                .offset(y = 571.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_zakoruchki),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(315.93.dp, 524.38.dp)
                .align(Alignment.TopCenter)
                .offset(y = 192.dp)
        )



        Button (
            onClick = { },//navController.navigate("second") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.back_button),
                contentColor = colorResource(id = R.color.back_button),
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .size(335.dp, 50.dp)
                .align(Alignment.TopCenter)
                .offset(y = 726.dp)

        ) {
            Text (
                "Начать",
                color = colorResource(id = R.color.text),
                //fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
            )
        }
    }
}










//@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun SecondScreen(){//navController: NavController){
    Box (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.gradient1),
                        colorResource(id = R.color.gradient2)
                    )
                )
            )
            .fillMaxSize(),
    ) {



        Image (
            painter = painterResource(id = R.drawable.welcome2_smile),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(45.dp, 45.dp)
                .offset(304.dp, 113.dp)
        )


        Image (
            painter = painterResource(id = R.drawable.welcome2_zakoruchka),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(54.dp, 90.dp)
                .offset(63.31.dp, 130.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_boot2),
            contentScale = ContentScale.Crop,
            contentDescription = "App title",
            modifier = Modifier
                .size(309.32.dp, 200.dp)
                .align(Alignment.TopCenter)
                .offset(y = 130.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.boot2_shadow),
            contentScale = ContentScale.Crop,
            contentDescription = "App title",
            modifier = Modifier
                .size(218.dp, 17.dp)
                .align(Alignment.TopCenter)
                .offset(y = 288.85.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome2_text),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(315.dp, 89.dp)
                .align(Alignment.TopCenter)
                .offset(y = 453.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome2_desc),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(322.dp, 48.dp)
                .align(Alignment.TopCenter)
                .offset(y = 554.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_indicator2),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(118.dp, 5.dp)
                .align(Alignment.TopCenter)
                .offset(y = 642.dp)
        )





        Button (
            onClick = { }, //navController.navigate("third") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.back_button),
                contentColor = colorResource(id = R.color.back_button),
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .fillMaxHeight(0.06F)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Далее",
                color = colorResource(id = R.color.text),
                //fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
            )
        }
    }
}







@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun ThirdScreen(){
    Box (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.gradient1),
                        colorResource(id = R.color.gradient2)
                    )
                )
            )
            .fillMaxSize(),
    ) {

        Image (
            painter = painterResource(id = R.drawable.welcome_boot3),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(465.47.dp)
                .align(Alignment.TopCenter)
                .offset(y = 45.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome3_text),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(315.dp, 89.dp)
                .align(Alignment.TopCenter)
                .offset(y = 453.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome3_desc),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(322.dp, 48.dp)
                .align(Alignment.TopCenter)
                .offset(y = 554.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_indicator3),
            contentScale = ContentScale.Fit,
            contentDescription = "App title",
            modifier = Modifier
                .size(118.dp, 5.dp)
                .align(Alignment.TopCenter)
                .offset(y = 642.dp)
        )



        Button (
            onClick = { }, //navController.navigate("third") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.back_button),
                contentColor = colorResource(id = R.color.back_button),
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .fillMaxHeight(0.06F)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Далее",
                color = colorResource(id = R.color.text),
                //fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
            )
        }
    }
}









