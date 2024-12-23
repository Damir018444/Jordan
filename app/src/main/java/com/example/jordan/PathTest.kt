package com.example.jordan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = false,
    device = "spec:width=375dp,height=812dp,dpi=440,isRound=true",
)
@Composable
fun PathTest(){

    val withPx = LocalContext.current.resources.displayMetrics.widthPixels

    val barShape = BarShape(
        offset = withPx / 2f,
        circleRadius = 70.dp,
        circleGap = 0.dp,
    )

    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.Red,
                contentColor = Color.Red
            ),
            shape = barShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
            modifier = Modifier
                .background(shape = barShape, color = Color.Red)
                .fillMaxWidth()
                .height(106.dp)
        ) {
            Box (contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
                Text(
                    ".|.",
                    color = Color.Green,
                    fontWeight = FontWeight(500),
                    fontSize = 26.sp,
                )
            }
        }
    }
}