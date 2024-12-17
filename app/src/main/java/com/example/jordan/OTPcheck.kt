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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jan.supabase.createSupabaseClient
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)
@Composable
fun OTPcheck(viewModel: SupabaseAuthViewModel = viewModel()){ //navController: NavController){

    val context = LocalContext.current
    var value by remember { mutableStateOf("") }
    val textFieldRequester = remember { FocusRequester() }

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
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(85.dp, 25.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

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


            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            BasicTextField(
                value = value,
                onValueChange = {
                    if(it.length <= 6) value = it
                },
                modifier = Modifier
                    //.background(Color.Red)
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



            Spacer(Modifier.height(25.dp))


            Button (
                onClick = {  }, //navController.navigate("third") },
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
                    //fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    fontWeight = FontWeight(600),
                    lineHeight = 22.sp,
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }
        }
    }
}

