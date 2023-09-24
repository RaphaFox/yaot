package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class AppColors(
    val content: Color,
    val component: Color,
    val background: Color,
    val text: Color,
)

@Immutable
data class AppDimensions(
    val smallPadding: Dp,
    val mediumPadding: Dp,
    val largePadding: Dp,
    val extraLargePadding: Dp,
)

@Immutable
data class AppTypography(
    val body: TextStyle,
    val title: TextStyle,
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        content = Color.Unspecified,
        component = Color.Unspecified,
        background = Color.Unspecified,
        text = Color.Unspecified,
    )
}

val LocalAppDimension = staticCompositionLocalOf {
    AppDimensions(
        smallPadding = Dp.Unspecified,
        mediumPadding = Dp.Unspecified,
        largePadding = Dp.Unspecified,
        extraLargePadding = Dp.Unspecified,
    )
}


val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        body = TextStyle.Default,
        title = TextStyle.Default,
    )
}
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColors = if (darkTheme) {
        AppColors(
            content = darkOrchidContent,
            component = darkPurpleComponent,
            background = darkGrayBackground,
            text = darkWhiteText,
        )
    } else {
        AppColors(
            content = lightOrchidContent,
            component = lightPurpleComponent,
            background = lightGrayBackground,
            text = lightBlackText,
        )
    }
    val appDimensions = AppDimensions(
        smallPadding = 8.dp,
        mediumPadding = 16.dp,
        largePadding = 24.dp,
        extraLargePadding = 32.dp,
    )
    val appTypography = AppTypography(
        body = TextStyle(
            fontSize = 16.sp,
            color = appColors.text,
        ),
        title = TextStyle(
            fontSize = 32.sp,
            color = appColors.text,
        ),
    )
    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalAppTypography provides appTypography,
        LocalAppDimension provides appDimensions,
        content = content
    )
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    val dimensions: AppDimensions
        @Composable
        get() = LocalAppDimension.current
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}
