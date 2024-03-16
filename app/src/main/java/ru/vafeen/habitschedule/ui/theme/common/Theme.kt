package ru.vafeen.habitschedule.ui.theme.common

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

data class HabitScheduleColors(
    val background: Color,
    val habitCardColor: Color,
)

val baseLightPalette = HabitScheduleColors(
    background = Color.White,
    habitCardColor = Color.LightGray
)

val baseDarkPalette = baseLightPalette.copy(
    background = Color.Black,
    habitCardColor = Color.Gray
)

@Composable
fun HabitScheduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        baseDarkPalette
    } else {
        baseLightPalette
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalColors provides colors,
        content = content
    )
}

object HabitScheduleTheme {
    val colors: HabitScheduleColors
        @Composable
        get() = LocalColors.current
}

val LocalColors = staticCompositionLocalOf<HabitScheduleColors> {
    error("Composition error")
}