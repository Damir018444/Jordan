package com.example.jordan

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jan.supabase.createSupabaseClient
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)
@Composable
fun SignIn(viewModel: SupabaseAuthViewModel = viewModel()){//navController: NavController){

    val context = LocalContext.current
    val userState by viewModel.userState

    var currentUserState by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }



    Box (
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .fillMaxSize()
    ) {

        Button(
            onClick = {}, //navController.navigate("first2") }
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
                tint = Color.Black
            )
        }





        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 121.dp)
        ) {

            Text(
                text = "Привет!",
                fontSize = 32.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700),
                lineHeight = 37.57.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(127.dp, 38.dp)
                    .align(Alignment.CenterHorizontally)

            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Заполните Свои Данные Или\nПродолжите Через Социальные Медиа",
                fontSize = 16.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )




            Spacer(Modifier.height(30.dp))






            Text(
                text = "Email",
                fontSize = 16.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(42.dp, 20.dp)
                    .align(Alignment.Start)
            )


            Spacer(Modifier.height(12.dp))


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



            /*TextField (
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("xyz@gmail.com") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .size(335.dp, 48.dp)
                    .align(Alignment.Start),
                colors = colors
            )*/

            BasicTextField(
                value = email,
                onValueChange = { email = it },
                textStyle = TextStyle.Default.copy(
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
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
                    placeholder = { Text("xyz@gmail.com") },
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                        top = 0.dp,
                        bottom = 0.dp,
                    ),
                    shape = RoundedCornerShape(14.dp),
                    colors = colors
                )
            }


            Spacer(Modifier.height(30.dp))


            Text(
                text = "Пароль",
                fontSize = 16.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(58.dp, 29.dp)
                    .align(Alignment.Start),
            )


            Spacer(Modifier.height(12.dp))


            /*TextField (
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Введите пароль") },
                singleLine = true,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton (onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = if (isPasswordVisible) rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.eye))
                            else rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.crossed_eye)),
                            contentDescription = if (isPasswordVisible) "Скрыть пароль" else "Показать пароль"
                        )
                    }
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .size(335.dp, 48.dp)
                    .align(Alignment.Start),
                colors = colors
            )*/


            BasicTextField(
                value = password,
                onValueChange = { password = it },
                textStyle = TextStyle.Default.copy(
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
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
                singleLine = true,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            ) {
                TextFieldDefaults.DecorationBox(
                    value = password,
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    placeholder = { Text("Введите пароль") },
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                        top = 0.dp,
                        bottom = 0.dp,
                    ),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton (onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                painter = if (isPasswordVisible) rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.eye))
                                else rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.crossed_eye)),
                                contentDescription = if (isPasswordVisible) "Скрыть пароль" else "Показать пароль"
                            )
                        }
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = colors
                )
            }


            Spacer(Modifier.height(12.dp))


            TextButton (
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = "Восстановить",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.gray),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    lineHeight = 16.sp,
                    textAlign = TextAlign.End,
                )
            }


            Spacer(Modifier.height(24.dp))


            Button (
                onClick = {
                    viewModel.signIn(
                        context,
                        email,
                        password
                    )
                }, //navController.navigate("third") },
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
                    "Войти",
                    color = colorResource(id = R.color.button_text2),
                    //fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    fontWeight = FontWeight(600),
                    lineHeight = 22.sp,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }


            when (userState) {
                is UserState.Loading -> {}

                is UserState.Success -> {
                    val message = (userState as UserState.Success).message
                    currentUserState = message
                }

                is UserState.Error -> {
                    val message = (userState as UserState.Error).message
                    currentUserState = message
                }
            }


            Spacer(modifier = Modifier.height(50.dp))


            Text (
                text = currentUserState,
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 18.78.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                //fontFamily = FontFamily(Font(R.font.raleway_regular)),
            )
        }















        val annotatedString = buildAnnotatedString {
            withStyle (
                style = SpanStyle(
                    color = colorResource(id = R.color.dark_gray),
                )
            ){
                append("Вы впервые? ")
            }

            pushStringAnnotation(tag = "create_user", annotation = "create_user")

            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.text),
                )
            ) {
                append("Создать пользователя")
            }
            pop()
        }

        Text (
            text = annotatedString,
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            lineHeight = 18.78.sp,
            textAlign = TextAlign.Center,
            //fontFamily = FontFamily(Font(R.font.raleway_regular)),
            modifier = Modifier
                .offset(y = 746.dp)
                .align(Alignment.TopCenter)
                .clickable {
                    //navController.navigate("first2") },
                }
        )
    }
}