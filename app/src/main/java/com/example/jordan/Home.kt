package com.example.jordan

import android.preference.PreferenceDataStore
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)
@Composable
fun Home(){

    val context = LocalContext.current



    var searchText by remember { mutableStateOf("") }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white2))
    ) {

        Icon(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.tri_palki)),
            contentDescription = "",
            modifier = Modifier
                .offset(x = 110.dp, y = 43.dp)
            //.align(Alignment.TopStart)
        )

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
        ) {

            Row (
                //horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                /*Button(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .wrapContentSize(),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent,
                    ),
                ) {*/
                Image(
                    painter = painterResource(id = R.drawable.hamburger),
                    contentDescription = "Menu",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        //.align(Alignment.CenterStart)
                        .size(width = 25.71.dp, height = 18.dp)
                        .clickable(
                            onClick = { },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )


                Spacer(modifier = Modifier.width(77.dp))


                Text (
                    text = "Главная",
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    lineHeight = 37.57.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )


                Spacer(modifier = Modifier.width(59.29.dp))


                BadgedBox(
                    //modifier = Modifier.align(Alignment.CenterEnd),
                    badge = { Badge(modifier = Modifier
                        .size(8.dp)
                        .offset(x = (-5.5).dp), containerColor = colorResource(id = R.color.pastel_red)) },
                ) {
                    Button(
                        onClick = { },//navController.navigate("signin") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.white),
                        ),
                        shape = RoundedCornerShape(40.dp),
                        contentPadding = PaddingValues(10.dp),
                        modifier = Modifier
                            .size(44.dp)
                    ) {
                        Icon(
                            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_fab)),
                            contentDescription = "Назад",
                            modifier = Modifier
                                .fillMaxSize(),
                            tint = colorResource(id = R.color.black)
                        )
                    }
                }
            }


            
            Spacer(modifier = Modifier.height(19.dp))



            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ){
                val colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(id = R.color.white),
                    focusedContainerColor = colorResource(id = R.color.white),

                    focusedTextColor = colorResource(id = R.color.tf_text),
                    unfocusedTextColor = colorResource(id = R.color.tf_text),

                    focusedPlaceholderColor = colorResource(id = R.color.tf_placeholder),
                    unfocusedPlaceholderColor = colorResource(id = R.color.tf_placeholder),

                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )

                BasicTextField (
                    value = searchText,
                    onValueChange = { searchText = it },
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
                        .weight(1f)
                        .shadow(4.dp, shape = RoundedCornerShape(14.dp)),

                    enabled = true,
                    singleLine = true
                ) {
                    TextFieldDefaults.DecorationBox(
                        value = searchText,
                        innerTextField = it,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        placeholder = {
                            Text(
                                "Поиск",
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = colorResource(id = R.color.tf_placeholder)
                            ) },
                        contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                            top = 0.dp,
                            bottom = 0.dp,
                            start = 31.dp
                        ),
                        shape = RoundedCornerShape(14.dp),
                        colors = colors,
                        leadingIcon = {
                            Icon(
                                painter = rememberVectorPainter(image = ImageVector.vectorResource(
                                id = R.drawable.search)),
                                contentDescription = null,
                                modifier = Modifier.padding(start = 30.dp)
                            )
                        }
                    )
                }

                Button(
                    onClick = { },//navController.navigate("signin") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.button_back2),
                    ),
                    shape = RoundedCornerShape(40.dp),
                    contentPadding = PaddingValues(14.dp),
                    modifier = Modifier
                        .size(52.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(40.dp))
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.sliders)),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }
            }



            Spacer(modifier = Modifier.height(24.dp))



            Text (
                text = "Категории",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                lineHeight = 18.78.sp,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                color = colorResource(id = R.color.text),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 22.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))



            val buttonTexts = listOf("Все", "Outdoor", "Tennis", "Running")

            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ){
                items(buttonTexts) { text ->

                    Button (
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.white),
                            contentColor = colorResource(id = R.color.white),
                        ),
                        shape = RoundedCornerShape(13.dp),
                        modifier = Modifier
                            .size(108.dp, 40.dp)

                    ) {
                        Text (
                            text = text,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            lineHeight = 18.sp,
                            color = colorResource(id = R.color.text),
                            modifier = Modifier
                        )
                    }
                }
            }



            Spacer(modifier = Modifier.height(24.dp))



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 11.5.dp, end = 28.5.dp)
            ){
                Text (
                    text = "Популярное",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    lineHeight = 24.sp,
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )

                Text (
                    text = "Все",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    lineHeight = 16.sp,
                    color = colorResource(id = R.color.hyperlink_text),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = { },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

















            Spacer(modifier = Modifier.height(40.5.dp))




            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 20.dp)
            ){
                Text (
                    text = "Акции",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    lineHeight = 18.78.sp,
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                )

                Text (
                    text = "Все",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    lineHeight = 16.sp,
                    color = colorResource(id = R.color.hyperlink_text),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = { },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }


            Spacer(modifier = Modifier.height(21.dp))


            Image(
                painter = painterResource(id = R.drawable.ad_png),
                contentDescription = "promotion",
                modifier = Modifier
                    .size(width = 335.dp, height = 95.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}