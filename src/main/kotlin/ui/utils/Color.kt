package ui.utils

import androidx.compose.ui.graphics.Color
import kotlin.math.abs

fun Color.manipulate(factor: Float) = Color(
    alpha = this.alpha,
    red = (this.red * abs(factor)).coerceAtMost(Color.White.red),
    green = (this.green * abs(factor)).coerceAtMost(Color.White.green),
    blue = (this.blue * abs(factor)).coerceAtMost(Color.White.blue)
)
