package com.example.jordan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun OTPcheck(navController: NavController, email: String){

    val viewModel: SupabaseViewModel = viewModel()

    var value by remember { mutableStateOf("") }

    val context = LocalContext.current

    var errorText by remember { mutableStateOf("") }

    var timeLeft by remember { mutableIntStateOf(60) }
    var isRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isRunning) {
        if (isRunning && timeLeft > 0) {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft -= 1
            }
            isRunning = false
        }
    }



    Box (
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .fillMaxSize()
    ) {

        Button(
            onClick = { navController.navigate("recovery") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_back1),
                contentColor = colorResource(id = R.color.button_back1),
            ),
            shape = CircleShape,
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .size(44.dp)
                .offset(x = 22.dp, y = 66.dp)

        ) {
            Icon(
                painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.back_button)),
                contentDescription = "Назад",
                modifier = Modifier
                    .fillMaxSize(),
                tint = colorResource(id = R.color.black)
            )
        }






        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 121.dp)
        ) {

            Text(
                text = "OTP проверка",
                fontSize = 32.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(700),
                lineHeight = 37.57.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(228.dp, 38.dp)
                    .align(Alignment.CenterHorizontally)

            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Пожалуйста, Проверьте Свою\nЭлектронную Почту, Чтобы Увидеть\nКод Подтверждения",
                fontSize = 16.sp,
                color = colorResource(id = R.color.gray),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(315.dp, 72.dp)
                    .align(Alignment.CenterHorizontally)
            )


            Spacer(Modifier.height(16.dp))


            Text(
                text = "OTP Код",
                fontWeight = FontWeight(600),
                fontSize = 21.sp,
                lineHeight = 24.65.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    //.size(85.dp, 25.dp)
                    .padding(start = 34.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))



            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            BasicTextField(
                value = value,
                onValueChange = {
                    if(it.length <= 6) {
                        value = it
                        if(it.length == 6){
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            viewModel.checkOTP(context, email, value) { code ->
                                errorText = when (code) {
                                    1 -> "Успешно" //navController.navigate("home") },
                                    -1 -> "Какая-та ошибка"
                                    -11 -> "Ошибка сети"
                                    -111 -> "Не авторизован"
                                    else -> "Какая-та ошибка"
                                }
                            }
                        }
                    } else {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                },
            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    repeat(6){ index ->
                        val number = when {
                            index >= value.length -> ""
                            else -> value[index]
                        }

                        Box (
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(46.dp, 99.dp)
                                .background(
                                    color = colorResource(id = R.color.tf_back),
                                    shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Text (
                                text = number.toString(),
                                fontWeight = FontWeight(600),
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = colorResource(id = R.color.text),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }



            Spacer(Modifier.height(22.dp))



            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Text (
                    text = "Отправить заново",
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    lineHeight = 14.09.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.another_gray),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(1F)
                )

                Text (
                    text = formatTime(timeLeft),
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    lineHeight = 14.09.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.another_gray),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1F)
                )
            }




            Spacer(modifier = Modifier.height(35.dp))

            val color = if(errorText == "Успешно") Color.Green
            else Color.Red

            Text (
                text = errorText,
                fontSize = 14.sp,
                color = color,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(500),
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(300.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}




fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format(Locale.getDefault(),"%02d:%02d", minutes, remainingSeconds)
}

