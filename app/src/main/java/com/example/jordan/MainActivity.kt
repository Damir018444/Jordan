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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
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
            Start()
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
        ) { FirstScreen(navController) }
        composable(
            "second",
            enterTransition = { fadeIn(animationSpec = tween(2000)) },
            exitTransition = { fadeOut(animationSpec = tween(2000)) }
            ) { SecondScreen(navController) }
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
private fun FirstScreen(navController: NavController){
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
            painter = painterResource(id = R.drawable.welcome_boot1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75F)
                //.offset(y = (-45).dp)
        )

        Button (
            onClick = { navController.navigate("second") },
            colors = ButtonColors(Color.White, Color.White, Color.White, Color.White),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .fillMaxHeight(0.06F)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Начать",
                color = Color.Black,
                //fontFamily = FontFamily(Font(R.font.raleway_bold)),
                fontSize = 14.sp,
                modifier = Modifier
                    //.fillMaxSize()
            )
        }
    }
}






//@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun SecondScreen(navController: NavController){
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
            painter = painterResource(id = R.drawable.welcome_boot1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75F)
            //.offset(y = (-45).dp)
        )

        Button (
            onClick = { navController.navigate("third") },
            colors = ButtonColors(Color.White, Color.White, Color.White, Color.White),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .fillMaxHeight(0.06F)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Начать",
                color = Color.Black,
                //fontFamily = FontFamily(Font(R.font.raleway_bold)),
                fontSize = 14.sp,
                modifier = Modifier
                //.fillMaxSize()
            )
        }
    }
}







//@Preview(showBackground = true, showSystemUi = false)
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
        contentAlignment = Alignment.Center
    ) {
        Image (
            painter = painterResource(id = R.drawable.welcome_boot1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75F)
            //.offset(y = (-45).dp)
        )

        Button (
            onClick = {  },
            colors = ButtonColors(Color.White, Color.White, Color.White, Color.White),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .fillMaxHeight(0.06F)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Начать",
                color = Color.Black,
                //fontFamily = FontFamily(Font(R.font.raleway_bold)),
                fontSize = 14.sp,
                modifier = Modifier
                //.fillMaxSize()
            )
        }
    }
}
