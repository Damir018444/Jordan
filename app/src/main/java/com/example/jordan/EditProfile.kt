package com.example.jordan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun EditProfile(navController: NavController) {

    val context = LocalContext.current
    val viewModel: SupabaseViewModel = viewModel()
    var imageUrl by remember { mutableStateOf("") }

    var isPressed by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }


    var entered by remember { mutableStateOf(false) }
    viewModel.getUserDataType1(context) { data ->
        if(!entered) {
            name = data[0]
            email = data[1]
            entered = true
        }
    }

    var errorText by remember { mutableStateOf("") }

    val supabaseUrl = "https://vopbzurfnxuvwuubeiip.supabase.co"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Button(
                    onClick = { navController.navigate("scrnavcont") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.button_back1),
                        contentColor = colorResource(id = R.color.button_back1),
                    ),
                    shape = CircleShape,
                    contentPadding = PaddingValues(10.dp),
                    modifier = Modifier
                        .size(44.dp)
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.back_button)),
                        contentDescription = "Назад",
                        modifier = Modifier
                            .fillMaxSize(),
                        tint = colorResource(id = R.color.black)
                    )
                }


                Text(
                    text = "Профиль",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }


            Spacer(modifier = Modifier.height(40.dp))


            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){



                viewModel.getFile(context,"pfp"){
                    imageUrl = it
                }

                if(imageUrl.isNotEmpty()){
                    AsyncImage (
                        url = imageUrl,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }



                /*Image(
                    painter = painterResource(id = R.drawable.chelik),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        //.align(Alignment.CenterHorizontally)
                )*/

                Button(
                    onClick = { navController.navigate("profile") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.button_back2),
                        contentColor = colorResource(id = R.color.button_back2),
                    ),
                    shape = CircleShape,
                    contentPadding = PaddingValues(6.dp),
                    border = BorderStroke(1.dp, colorResource(id = R.color.white)),
                    modifier = Modifier
                        .size(21.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.pencil)),
                        contentDescription = "Назад",
                        modifier = Modifier
                            .fillMaxSize(),
                        tint = colorResource(id = R.color.white)
                    )
                }
            }





            Spacer(modifier = Modifier.height(40.dp))





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

            Column (
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

                Text(
                    text = "Ваше имя",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                BasicTextField(
                    value = name,
                    onValueChange = { name = it },
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
                        value = name,
                        innerTextField = it,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        placeholder = {
                            Text(
                                "xxxxxxxx",
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






                Text(
                    text = "Email",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
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
            }




            Spacer(Modifier.height(30.dp))




            Column (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Пароль",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                Spacer(Modifier.height(12.dp))

                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
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
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                ) {
                    TextFieldDefaults.DecorationBox(
                        value = password,
                        innerTextField = it,
                        enabled = true,
                        singleLine = true,
                        interactionSource = remember { MutableInteractionSource() },
                        placeholder = {
                            Text(
                                "********",
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

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Восстановить",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = colorResource(id = R.color.gray),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { }//navController.navigate("signup") }
                        )
                )
            }



            Spacer(Modifier.height(30.dp))



            Button (
                onClick = {
                    if(isPressed) return@Button
                    isPressed = true
                    if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if(password.isNotEmpty() and password.isNotBlank() and (password.length >= 8)) {
                            viewModel.updateDataType1(context, name, email, password) { code ->
                                errorText = when (code) {
                                    1 -> "Успешно" //navController.navigate("home")
                                    -1 -> "Ошибка"
                                    -11 -> "authId равен NULL"
                                    else -> "Какая-та ошибка"
                                }
                                isPressed = false
                            }
                        } else errorText = "Введите пароль от аккаунта!"
                    } else errorText = "Введите действительный адрес эл. почты!"
                    isPressed = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_back2),
                    contentColor = colorResource(id = R.color.button_back2),
                ),
                shape = RoundedCornerShape(13.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text (
                    "Сохранить",
                    color = colorResource(id = R.color.button_text2),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    fontWeight = FontWeight(600),
                    lineHeight = 22.sp,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }





            Spacer(modifier = Modifier.height(25.dp))

            val color = if(errorText == "Успешно") Color.Green
            else Color.Red

            Text(
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



@Composable
fun AsyncImage(url: String, modifier: Modifier = Modifier) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var errDrawable by remember { mutableStateOf<Drawable?>(null) }
    var placeDrawable by remember { mutableStateOf<Drawable?>(null) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        placeDrawable = ContextCompat.getDrawable(context, R.drawable.pfp_placeholder)
        errDrawable = ContextCompat.getDrawable(context, R.drawable.pfp_placeholder)
    }

    val target = remember(url) {
        object : Target {
            override fun onBitmapLoaded(loadedBitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap = loadedBitmap
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                bitmap = null
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }
        }
    }

    LaunchedEffect(url) {
        Picasso.get().load(url).placeholder(placeDrawable!!).error(errDrawable!!).into(target)
    }

    bitmap?.let {
        Image(
            painter = BitmapPainter(it.asImageBitmap()),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } ?: errDrawable?.let {
        Image(
            painter = BitmapPainter(it.toBitmap().asImageBitmap()),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}