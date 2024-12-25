package com.example.jordan

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun ForgotPass(navController: NavController) {

    val viewModel: SupabaseViewModel = viewModel()

    var isPressed by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }

    var errorText by remember { mutableStateOf("") }

    Box (
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .fillMaxSize()
            .then(
                if (showDialog) Modifier
                    .blur(
                        4.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    ) else Modifier
            )
    ) {

        Button(
            onClick = { navController.navigate("signin") },
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
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 121.dp)
        ) {

            Text(
                text = "Забыл пароль",
                fontSize = 32.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(700),
                lineHeight = 37.57.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(227.dp, 38.dp)
                    .align(Alignment.CenterHorizontally)

            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Введите Свою Учетную Запись\nДля Сброса",
                fontSize = 16.sp,
                color = colorResource(id = R.color.gray),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(315.dp, 48.dp)
                    .align(Alignment.CenterHorizontally)
            )


            Spacer(Modifier.height(40.dp))


            val colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.tf_back),
                focusedContainerColor = colorResource(id = R.color.tf_back),

                focusedTextColor = colorResource(id = R.color.tf_text),
                unfocusedTextColor = colorResource(id = R.color.tf_text),

                focusedPlaceholderColor = colorResource(id = R.color.tf_placeholder),
                unfocusedPlaceholderColor = colorResource(id = R.color.tf_placeholder),

                focusedLabelColor = colorResource(id = R.color.text),
                unfocusedLabelColor = colorResource(id = R.color.text),

                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )

            BasicTextField(
                value = email,
                onValueChange = { email = it },
                textStyle = TextStyle.Default.copy(
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = colorResource(id = R.color.tf_text),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.tf_back),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .indicatorLine(
                        enabled = false,
                        isError = false,
                        interactionSource = remember { MutableInteractionSource() },
                        colors = OutlinedTextFieldDefaults.colors(),
                        focusedIndicatorLineThickness = 0.dp,
                        unfocusedIndicatorLineThickness = 0.dp
                    )
                    .size(335.dp, 48.dp)
                    .align(Alignment.Start),

                enabled = true,
                singleLine = true
            ) {
                TextFieldDefaults.DecorationBox(
                    value = email,
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    placeholder = {
                        Text(
                            "xyz@gmail.com",
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = colorResource(id = R.color.tf_placeholder)
                        ) },
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                        top = 0.dp,
                        bottom = 0.dp,
                    ),
                    shape = RoundedCornerShape(14.dp),
                    colors = colors
                )
            }


            Spacer(Modifier.height(40.dp))


            Button (
                onClick = {
                    if(isPressed) return@Button
                    isPressed = true
                    if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        viewModel.getOTP(email) { code ->
                            if(code > 0) showDialog = true
                            else errorText = "Какая-та ошибка"
                            isPressed = false
                        }
                    } else errorText = "Введите действительный адрес эл. почты"
                    isPressed = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_back2),
                    contentColor = colorResource(id = R.color.button_back2),
                ),
                shape = RoundedCornerShape(13.dp),
                modifier = Modifier
                    .size(335.dp, 50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text (
                    "Отправить",
                    color = colorResource(id = R.color.button_text2),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    fontWeight = FontWeight(600),
                    lineHeight = 22.sp,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }






            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = errorText,
                fontSize = 14.sp,
                color = Color.Red,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(500),
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(250.dp)
                    .align(Alignment.CenterHorizontally)
            )




        }
        if (showDialog) {
            ShowDialog()

            LaunchedEffect(Unit) {
                delay(1200)
                showDialog = false
                navController.navigate("recovery2/$email")
            }
        }
    }
}


/*@Preview(showBackground = false, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun ShowDialog() {

    Box(
        modifier = Modifier
            .offset(y = 295.dp)
    ){
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .size(335.dp, 196.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colorResource(id = R.color.white)),
            ){
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box (
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                color = colorResource(id = R.color.button_back2),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.email)),
                            contentDescription = "?",
                            tint = colorResource(id = R.color.ic_email)
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "Проверьте Ваш Email",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = colorResource(id = R.color.text),
                        fontFamily = FontFamily(Font(R.font.raleway_regular)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .size(315.dp,20.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Мы Отправили Код Восстановления\nПароля На Вашу Электронную Почту.",
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = colorResource(id = R.color.gray),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .size(315.dp,40.dp)
                    )
                }
            }
        }
    }
}