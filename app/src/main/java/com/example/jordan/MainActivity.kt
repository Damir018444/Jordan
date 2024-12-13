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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
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
            FirstScreen()
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
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF007FFF), // Верхний цвет
                        Color(0xFF56CCF2)  // Нижний цвет
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp)) // Для отступа сверху

            Text(
                text = "ДОБРО\nПОЖАЛОВАТЬ",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                lineHeight = 34.sp
            )

            Image(
                painter = painterResource(id = R.drawable.welcome_boot1), // Замените на ваш ресурс
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp) // Настройте размер изображения
            )

            Spacer(modifier = Modifier.height(16.dp)) // Отступ между изображением и линией

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp, 4.dp)
                        .background(Color.Gray, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp, 4.dp)
                        .background(Color.White, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .size(12.dp, 4.dp)
                        .background(Color.Gray, RoundedCornerShape(2.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Действие по нажатию */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color.White, Color.White, Color.White, Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Начать",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}












@Preview(showBackground = true, showSystemUi = false)
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
        //contentAlignment = Alignment.Center
    ) {

        Image (
            painter = painterResource(id = R.drawable.welcome_text1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(267.dp, 70.dp)
                .align(Alignment.TopCenter)
                //.padding(bottom = 121.dp)
                .offset(y = 121.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.zakoruchka4),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(27.dp, 30.dp)
                //.padding(start = 59.dp, top = 102.dp)
                .offset(59.dp, 102.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.zakoruchka3),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(134.dp, 18.dp)
                .align(Alignment.TopCenter)
                .offset(y = 210.dp)
            //.offset((-162).dp, 102.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_boot1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(671.37.dp, 588.05.dp)
                .align(Alignment.TopCenter)
                //.padding(top = 102.dp)
                .offset(y = 102.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.welcome_indicator1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(118.dp, 5.dp)
                .align(Alignment.TopCenter)
                //.padding(top = 102.dp)
                .offset(y = 571.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.zakoruchki_group),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .size(315.93.dp, 524.38.dp)
                .align(Alignment.TopCenter)
                //.padding(top = 102.dp)
                .offset(y = 192.dp)
        )










        Button (
            onClick = { },//navController.navigate("second") },
            colors = ButtonColors(Color.White, Color.White, Color.White, Color.White),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .size(335.dp, 50.dp)
                .align(Alignment.TopCenter)
                //.padding(top = 726.dp)
                .offset(y = 726.dp)
                //.align(Alignment.BottomCenter)

        ) {
            Text (
                "Начать",
                color = Color.Black,
                //fontFamily = FontFamily(Font(R.font.raleway_bold)),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
                //.fillMaxSize()
            )
        }
    }
}












/*@Preview(showBackground = true, showSystemUi = false)
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
        contentAlignment = Alignment.Center
    ) {
        Image (
            painter = painterResource(id = R.drawable.welcome_fon1),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .fillMaxSize()
                //.offset(y = (-45).dp)
        )

        Button (
            onClick = { },//navController.navigate("second") },
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
}*/






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
            onClick = { }, //navController.navigate("third") },
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
