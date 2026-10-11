package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = GroceryGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = GroceryGreenContainer,
    onPrimaryContainer = GroceryGreenDark,
    secondary = GroceryGreenSecondary,
    onSecondary = Color.White,
    background = BackgroundLight,
    surface = SurfaceLight,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun GroceryTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
