package com.example.jordan

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private lateinit var navigationViewModel: NavHostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel = ViewModelProvider(this)[NavHostViewModel::class.java]

        enableEdgeToEdge()
        setContent {

            //PathTest()

            //Start()
            //SignIn()
            //SignUp()
            //ForgotPass()
            //OTPcheck()

            //Home()

            //ScreensNavController()






            val navController = rememberNavController()
            navigationViewModel.navController.value = navController

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController) }

                //REGISTRATION/AUTHENTICATION
                composable(
                    "first",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
                ) { FirstScreen(navController) }
                composable(
                    "second",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { SecondScreen(navController) }
                composable(
                    "third",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { ThirdScreen(navController) }
                composable(
                    "signin",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { SignIn(navController) }
                composable(
                    "signup",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { SignUp(navController) }
                composable(
                    "recovery",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { ForgotPass(navController) }
                composable(
                    "recovery2/{email}",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email")
                    OTPcheck(navController, email!!)
                }





                //HOME/CART/ORDERS
                composable(
                    "scrnavcont",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
                ) { ScreensNavController(navController) }
                composable(
                    "home",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { Home(navController) }
                composable(
                    "favourites",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) {  }
                /*composable(
                    "signin",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) {  }
                composable(
                    "signup",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) {  }
                composable(
                    "recovery",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) {  }
*/





                //MENU/SETTINGS/SEARCH
                composable(
                    "home",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
                ) { Home(navController) }
                composable(
                    "notifications",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { Notifications(navController) }
                composable(
                    "edit_profile",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { EditProfile(navController) }
                composable(
                    "profile",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { Profile(navController) }
                composable(
                    "search",
                    enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                    exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
                ) { Search(navController) }
            }
        }
    }
}



/*@Composable
fun Start(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable(
            "first",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)) },
        ) { FirstScreen(navController) }
        composable(
            "second",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
        ) { SecondScreen(navController) }
        composable(
            "third",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
        ) { ThirdScreen(navController) }
        composable(
            "signin",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
        ) { SignIn(navController) }
        composable(
            "signup",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
        ) { SignUp(navController) }
        composable(
            "recovery",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
        ) { ForgotPass(navController) }
        composable(
            "recovery2/{email}",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)) },
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            OTPcheck(navController, email!!)
        }
    }
}*/




/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
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








/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
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
    ) {

        Text (
            "Добро пожаловать",
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(900),
            fontFamily = FontFamily(Font(R.font.raleway_regular)),
            fontSize = 35.sp,
            lineHeight = 35.22.sp,
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
            onClick = { navController.navigate("second") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_back1),
                contentColor = colorResource(id = R.color.button_back1),
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
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(600),
                lineHeight = 16.44.sp,
                fontSize = 14.sp,
                modifier = Modifier
            )
        }
    }
}










/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
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
    ) {




        Image (
            painter = painterResource(id = R.drawable.welcome_fon2),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App title",
            modifier = Modifier
                .fillMaxSize()

        )

        Text (
            "Начёнем путешествие",
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(700),
            fontFamily = FontFamily(Font(R.font.raleway_regular)),
            fontSize = 34.sp,
            lineHeight = 44.2.sp,
            modifier = Modifier
                .size(315.dp, 89.dp)
                .align(Alignment.TopCenter)
                .offset(y = 453.dp)
        )

        Text (
            "Умная, великолепная и модная\nколлекция Изучите сейчас",
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(400),
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            modifier = Modifier
                .size(388.85.dp, 48.dp)
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
            onClick = { navController.navigate("third") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_back1),
                contentColor = colorResource(id = R.color.button_back1),
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .size(335.dp, 50.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Далее",
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(600),
                lineHeight = 16.44.sp,
                fontSize = 14.sp,
                modifier = Modifier
            )
        }
    }
}







//@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun ThirdScreen(navController: NavController){
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

        Text (
            "У Вас Есть Сила, Чтобы",
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(700),
            fontFamily = FontFamily(Font(R.font.raleway_regular)),
            fontSize = 34.sp,
            lineHeight = 44.2.sp,
            modifier = Modifier
                .size(315.dp, 89.dp)
                .align(Alignment.TopCenter)
                .offset(y = 453.dp)
        )

        Text (
            "В вашей комнате много красивых и привлекательных растений",
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(400),
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 16.sp,
            lineHeight = 24.sp,
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
            onClick = { navController.navigate("signin") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_back1),
                contentColor = colorResource(id = R.color.button_back1),
            ),
            shape = RoundedCornerShape(13.dp),
            modifier = Modifier
                .size(335.dp, 50.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
        ) {
            Text (
                "Далее",
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(600),
                lineHeight = 16.44.sp,
                fontSize = 14.sp,
                modifier = Modifier
            )
        }
    }
}









