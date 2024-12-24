package com.example.jordan

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import java.time.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import okhttp3.internal.notifyAll
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local


@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)
@Composable
fun Notifications(){

    val boxBack = colorResource(id = R.color.white2)

    val notifTitle = "Заголовок"
    val notifText = "Lorem ipsum dolor sit amet consectetur. Venenatis pulvinar lobortis risus consectetur morbi egestas id in bibendum. Volutpat nulla urna sit sed diam nulla."

    val c = Calendar.getInstance()

    val currentDateTime = LocalDateTime.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
        c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))

    val formattedDate = currentDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"))

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Button (
                    onClick = {  },//navController.navigate("signin") },
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


                Text (
                    text = "Уведомления",
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



            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    //.weight(1f)
                    .padding(start = 21.dp, end = 26.dp)
            ) {

                repeat(4) {
                    item {
                        Column (
                            modifier = Modifier
                                .background(color = boxBack, shape = RoundedCornerShape(16.dp))
                                .weight(1f)
                        ) {
                            Text (
                                text = notifTitle,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 16.sp,
                                //fontFamily = FontFamily(Font(R.font.masiva_regular)),
                                color = colorResource(id = R.color.text),
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                            )

                            Text (
                                text = notifText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 14.4.sp,
                                //fontFamily = FontFamily(Font(R.font.masiva_regular)),
                                color = colorResource(id = R.color.text),
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            )

                            Text (
                                text = formattedDate,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 14.4.sp,
                                //fontFamily = FontFamily(Font(R.font.masiva_regular)),
                                color = colorResource(id = R.color.text),
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}