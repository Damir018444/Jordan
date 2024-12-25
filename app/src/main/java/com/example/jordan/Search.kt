package com.example.jordan

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)*/
@Composable
fun Search(navController: NavController) {

    val prefHelper = SharedPreferenceHelper(LocalContext.current)

    var searchText by remember { mutableStateOf("") }

    var num by remember { mutableIntStateOf(prefHelper.getIntData("search_count")) }
    var history by remember { mutableStateOf(prefHelper.getStringSetData("history")?.toList() ?: emptyList() )}

    Log.e("history", history.joinToString(", "))

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white2))
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(26.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white2))
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
                    text = "Поиск",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }


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

            BasicTextField(
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
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
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
                        )
                    },
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                        top = 0.dp,
                        bottom = 0.dp,
                        start = 16.84.dp
                    ),
                    shape = RoundedCornerShape(14.dp),
                    colors = colors,
                    leadingIcon = {
                        Icon(
                            painter = rememberVectorPainter(
                                image = ImageVector.vectorResource(
                                    id = R.drawable.search
                                )
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 18.83.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = {
                                        val newHistory = history.plus(num.toString()+searchText).toSet()
                                        num += 1
                                        prefHelper.saveIntData("search_count", num)
                                        prefHelper.saveStringSetData("history", newHistory)

                                        num = prefHelper.getIntData("search_count")
                                        history = prefHelper.getStringSetData("history")?.toList() ?: emptyList()
                                    }
                                )
                        )
                    },

                    trailingIcon = {
                        Icon(
                            painter = rememberVectorPainter(
                                image = ImageVector.vectorResource(
                                    id = R.drawable.microphone
                                )
                            ),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 15.dp)
                        )
                    }
                )
            }




            if(history.isNotEmpty()) {
                val indexedWords = mutableListOf<Pair<Int, String>>()

                for (item in history) {
                    val matchResult = Regex("(\\d+)([\\s\\S]*)").find(item)
                    if (matchResult != null) {
                        val index = matchResult.groupValues[1].toInt()
                        val word = matchResult.groupValues[2].trim()
                        indexedWords.add(Pair(index, word))
                    }
                }

                val sortedIndexedWords = indexedWords.sortedBy { it.first }

                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(start = 21.dp)
                ) {
                    for (pair in sortedIndexedWords) {
                        item {
                            Row (
                                horizontalArrangement = Arrangement.spacedBy(14.75.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(
                                        image = ImageVector.vectorResource(
                                            id = R.drawable.clock
                                        )
                                    ),
                                    contentDescription = null,
                                )
                                Text(
                                    text = pair.second,
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                                    color = colorResource(id = R.color.text),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .clickable(
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() },
                                            onClick = { searchText = pair.second }
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}