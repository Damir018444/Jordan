package com.example.jordan

import android.util.Log
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BarShape(
    private val offset: Float,
    private val circleRadius: Dp,
    private val circleGap: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline  {
        return Outline.Generic(getPath(size, density))
    }

    private fun getPath(size: Size, density: Density): Path {

        val cutoutCenterX = offset
        val cutoutRadius = density.run { (circleRadius + circleGap).toPx() }

        return Path().apply {
            val cutoutEdgeOffset = cutoutRadius * 1.5f
            val cutoutLeftX = cutoutCenterX - cutoutEdgeOffset
            val cutoutRightX = cutoutCenterX + cutoutEdgeOffset

            Log.e("cutoutLeftX", cutoutLeftX.toString())

            val yOffset = density.run { 20.5.dp.toPx() }

            moveTo(x = 0F, y = size.height)

            lineTo(x = 0F, y = 0f)

            quadraticTo(x1 = cutoutLeftX/2, y1 = yOffset, x2 = cutoutLeftX, y2 = yOffset)

            cubicTo(
                x1 = cutoutCenterX,
                y1 = yOffset,
                x2 = cutoutCenterX - cutoutRadius,
                y2 = cutoutRadius,
                x3 = cutoutCenterX,
                y3 = cutoutRadius,
            )

            cubicTo(
                x1 = cutoutCenterX + cutoutRadius,
                y1 = cutoutRadius,
                x2 = cutoutCenterX,
                y2 = yOffset,
                x3 = cutoutRightX,
                y3 = yOffset,
            )

            //moveTo(x = size.width, y = 0f)

            quadraticTo(x1 = size.width - 110f, y1 = yOffset, x2 = size.width, y2 = 0f)
            lineTo(x = size.width, y = size.height)

            close()
        }
    }
}