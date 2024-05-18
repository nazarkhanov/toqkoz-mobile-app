package com.toqkoz.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme =  darkColorScheme(
    primary = Blue80,
    secondary = BlueGrey80,
    tertiary = Yellow80,
    secondaryContainer = BlueGrey40,
    onSecondaryContainer = Color.White,
    inverseOnSurface = BlueGrey40,
    error = Red80,
    surfaceVariant = Yellow80,

//    onPrimary = Color.Red,
//    primaryContainer = Color.Red,
//    onPrimaryContainer = Color.Red,
//    onSecondary = Color.Red,

//    onTertiary = Color.Red,
//    tertiaryContainer = Color.Red,
//    onTertiaryContainer = Color.Red,
//    error = Color.Red,
//    onError = Color.Red,
//    errorContainer = Color.Red,
//    onErrorContainer = Color.Red,
//    background = Color.Red,
//    onBackground = Color.Red,
//    surface = Color.Red,
//    onSurface = Color.Red,
//    surfaceVariant = Color.Red,
//    onSurfaceVariant = Color.Red,
//    outline = Color.Red,
//    outlineVariant = Color.Red,
//    scrim = Color.Red,
//    inverseSurface = Color.Red,
//    inversePrimary = Color.Red,
//    surfaceDim = Color.Red,
//    surfaceBright = Color.Red,
//    surfaceContainerLowest = Color.Red,
//    surfaceContainerLow = Color.Red,
//    surfaceContainer = Color.Red,
//    surfaceContainerHigh = Color.Red,
//    surfaceContainerHighest = Color.Red,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = BlueGrey40,
    tertiary = Yellow40,
    secondaryContainer = Blue80,
    inverseOnSurface = Gray40,
    surfaceVariant = Gray10,
    outline = Gray40,
)

@Composable
fun ToqkozTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val isDarkTheme = false

    val colorScheme = when {
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}