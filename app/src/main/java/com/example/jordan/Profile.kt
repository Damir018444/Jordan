package com.example.jordan

import android.net.Uri
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun Profile( navController: NavController) {

    val context = LocalContext.current
    val viewModel: SupabaseViewModel = viewModel()
    var imageUrl by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var isPressed by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    var isDropdownExpanded by remember { mutableStateOf(false) }

    val phoneCodes = listOf(1, 7, 20, 30, 31, 32, 33, 34, 36, 39, 41, 44, 49, 54, 55, 61, 82, 86, 91, 98, 998)

    var selectedItem by remember { mutableIntStateOf(phoneCodes[0]) }

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userPhoneFormatted by remember { mutableStateOf("") }

    var entered by remember { mutableStateOf(false) }

    viewModel.getUserDataType2(context) { data ->
        if(!entered) {
            name = data[0]
            surname = data[1]
            address = data[2]
            userPhone = data[3]
            entered = true
        }
    }





    val supabaseUrl = "https://vopbzurfnxuvwuubeiip.supabase.co"



    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri

        val imageBytearray = imageUri?.uriToByteArray(context)
        imageBytearray?.let {
            viewModel.uploadFile(context,"pfp", it) { callback ->
                errorText = if(callback > 0) "Картинка Успешно Установлена!"
                else "Ошибка при Установки Картинки!"
            }
        }
    }




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
                    onClick = { navController.navigate("edit_profile") },
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




                Text(
                    text = "Готово",
                    fontSize = 15.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.hyperlink_text),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(bottom = 4.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {


                                Log.e("PHONE","+$selectedItem${userPhone.replace("-","")}")

                                if(isPressed) return@clickable
                                isPressed = true
                                if(name.trim().isNotEmpty()) {
                                    if(surname.trim().isNotEmpty()) {
                                        if(address.trim().isNotEmpty()) {
                                            if(userPhone.trim().isNotEmpty()) {
                                                if(userPhone.trim().length != 10) {
                                                    viewModel.updateDataType2(context, name, surname, "+$selectedItem${userPhone.replace("-","")}", address) { code ->
                                                        errorText = when (code) {
                                                            1 -> { navController.navigate("edit_profile"); "Успешно" }
                                                            -1 -> "Ошибка"
                                                            -11 -> "authId равен NULL"
                                                            else -> "Какая-та ошибка"
                                                        }
                                                        isPressed = false
                                                    }
                                                } else errorText = "Введите корректный номер телефона!"
                                            } else errorText = "Введите Номер Телефона!"
                                        } else errorText = "Введите Адрес!"
                                    } else errorText = "Введите Фамилию!"
                                } else errorText = "Введите Имя"
                                isPressed = false
                            }
                        )
                )
            }



            Spacer(modifier = Modifier.height(40.dp))



            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){



                viewModel.getFile(context,"pfp"){
                    imageUrl = "$supabaseUrl/storage/v1/$it"
                    Log.e("imageUrl", imageUrl)
                }

                if(imageUrl.isNotEmpty()){
                    AsyncImage (
                        url = imageUrl,
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                }



                /*Image (
                    painter = painterResource(id = R.drawable.chelik),
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                )*/



                Text(
                    text = "Emmanuel Oyiboke",
                    fontSize = 20.sp,
                    lineHeight = 23.48.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )


                Text (
                    text = "Изменить фото профиля",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.hyperlink_text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable (
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                launcher.launch("image/*")
                            }
                        )
                )
            }



            Spacer(modifier = Modifier.height(11.dp))




            Box (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    //.padding(start = 15.dp, end = 23.dp)
                    .background(
                        color = colorResource(id = R.color.white2),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {

                Image (
                    painter = painterResource(id = R.drawable.barcode),
                    contentDescription = "bar code",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 321.dp, height = 70.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Text (
                    text = "Открыть",
                    fontSize = 12.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .rotate(-90F)
                        .align(Alignment.CenterStart)
                )
            }





            Spacer(modifier = Modifier.height(1.dp))





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
                //verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(start = 22.dp, end = 18.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

                Text(
                    text = "Имя",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(17.dp))

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
                            )
                        },
                        contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                            top = 0.dp,
                            bottom = 0.dp,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        colors = colors
                    )
                }



                Spacer(modifier = Modifier.height(16.dp))



                Text(
                    text = "Фамилия",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(17.dp))

                BasicTextField(
                    value = surname,
                    onValueChange = { surname = it },
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
                        value = surname,
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
                            )
                        },
                        contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                            top = 0.dp,
                            bottom = 0.dp,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        colors = colors
                    )
                }




                Spacer(modifier = Modifier.height(16.dp))




                Text(
                    text = "Адрес",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(17.dp))

                BasicTextField(
                    value = address,
                    onValueChange = { address = it },
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
                        value = address,
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
                            )
                        },
                        contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                            top = 0.dp,
                            bottom = 0.dp,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        colors = colors
                    )
                }




                Spacer(modifier = Modifier.height(16.dp))




                Text (
                    text = "Телефон",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Start)
                )


                Spacer(modifier = Modifier.height(17.dp))


                BasicTextField(
                    value = userPhoneFormatted,
                    onValueChange = {
                        if(it.length <= 12) {
                            userPhone = it
                            userPhoneFormatted = formatPhoneNumber(it)
                        }
                    },
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
                        value = userPhoneFormatted,
                        innerTextField = it,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        placeholder = {
                            Text(
                                "xxx-xxx-xxxx",
                                fontWeight = FontWeight(500),
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = colorResource(id = R.color.tf_placeholder)
                            )
                        },
                        contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                            top = 0.dp,
                            bottom = 0.dp,
                        ),

                        shape = RoundedCornerShape(14.dp),
                        colors = colors,

                        leadingIcon = {

                            Box {
                                Text("+$selectedItem", modifier = Modifier
                                    .clickable(onClick = { isDropdownExpanded = true })
                                    .padding(16.dp))
                                DropdownMenu(
                                    expanded = isDropdownExpanded,
                                    onDismissRequest = { isDropdownExpanded = false }
                                ) {
                                    phoneCodes.forEach { code ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedItem = code
                                                isDropdownExpanded = false
                                            },
                                            text = {
                                                Text (
                                                    text = "+$code",
                                                    fontSize = 16.sp,
                                                    lineHeight = 16.sp,
                                                    fontWeight = FontWeight(400),
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    color = colorResource(id = R.color.text),
                                                    textAlign = TextAlign.Center,
                                                )
                                            },
                                            modifier = Modifier
                                                .background(color = colorResource(id = R.color.white))

                                            /*trailingIcon = {
                                                Icon (
                                                    painter = rememberVectorPainter(
                                                        image = ImageVector.vectorResource(
                                                            id = R.drawable.dropdown
                                                        )
                                                    ),
                                                    contentDescription = null,
                                                    //modifier = Modifier.padding(end = 15.dp)
                                                )
                                            }*/
                                        )
                                    }
                                }
                            }
                        },
                    )
                }


                Spacer(modifier = Modifier.height(25.dp))

                val color = if(errorText.lowercase().contains("успешно")) Color.Green
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
}





fun formatPhoneNumber(input: String): String {
   val digitsOnly = input.filter { it.isDigit() }
    return when (digitsOnly.length) {
        in 0..3 -> digitsOnly
        4 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3)}"
        in 5..6 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3)}"
        in 7..10 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3, 6)}-${digitsOnly.substring(6)}"
        else -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3, 6)}-${digitsOnly.substring(6, 10)}"
    }
}