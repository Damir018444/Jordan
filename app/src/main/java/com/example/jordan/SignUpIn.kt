package com.example.jordan

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SignIn(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box (
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .fillMaxSize()
    ) {

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.back_button),
                contentColor = colorResource(id = R.color.back_button),
            ),
            shape = CircleShape,
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .size(44.dp)
                .offset(x = 22.dp, y = 66.dp)

        ) {
            Icon(
                painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.back_button)),
                contentDescription = "Описание изображения",
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
            Box (
                modifier = Modifier
                    .size(315.dp, 94.dp)
            ) {
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
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

                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Заполните Свои данные или продолжите через социальные медиа",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .size(315.dp, 48.dp)
                    )
                }
            }




            Spacer(Modifier.height(30.dp))




            Box (
                modifier = Modifier
                    .size(315.dp, 292.dp)
            ) {
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
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
                    )


                    Spacer(Modifier.height(12.dp))


                    TextField (
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("xyz@gmail.com") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .size(335.dp, 48.dp),
                        colors = OutlinedTextFieldDefaults.colors(
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
                    )


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
                    )


                    Spacer(Modifier.height(12.dp))


                    TextField (
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Введите пароль") },
                        singleLine = true,
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton (onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    painter = if (isPasswordVisible) rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.crossed_eye))
                                    else rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.crossed_eye)),
                                    contentDescription = if (isPasswordVisible) "Скрыть пароль" else "Показать пароль"
                                )
                            }
                        },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .size(335.dp, 48.dp),
                        colors = OutlinedTextFieldDefaults.colors(
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
                    )


                    Spacer(Modifier.height(12.dp))


                    TextButton (
                        onClick = {  },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = "Восстановить",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.text),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            lineHeight = 16.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .size(76.dp, 16.dp)
                        )
                    }
                }
            }
        }
    }
}