package com.example.jordan

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)
@Composable
fun Home(){

    val context = LocalContext.current

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white2))
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 30.dp, top = 48.dp)
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
                        .align(Alignment.CenterStart)
                        .size(width = 25.71.dp, height = 18.dp)
                        .clickable(
                            onClick = { },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )







            Icon(
                painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.tri_palki)),
                contentDescription = "",
                modifier = Modifier
                    .offset(x = 89.dp, y = (-5).dp)
                    .align(Alignment.TopStart)
            )

            Text (
                text = "Главная",
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
                lineHeight = 37.57.sp,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                color = colorResource(id = R.color.text),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )

            



            BadgedBox(
                modifier = Modifier.align(Alignment.CenterEnd),
                badge = { Badge(modifier = Modifier
                    .size(8.dp)
                    .offset(x = (-5.5).dp), containerColor = colorResource(id = R.color.pastel_red)) },
            ) {
                Button(
                    onClick = { },//navController.navigate("signin") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white),
                        contentColor = colorResource(id = R.color.white),
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
                        tint = Color.Black
                    )
                }
            }
        }
    }
}