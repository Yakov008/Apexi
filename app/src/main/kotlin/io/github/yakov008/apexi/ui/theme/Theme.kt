package io.github.yakov008.apexi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Temporary palette for M0; the visual design is a separate step.
private val ApexOrange = Color(0xFFE8590C)
private val ApexOrangeLight = Color(0xFFFF8A4C)

private val LightColors = lightColorScheme(primary = ApexOrange)
private val DarkColors = darkColorScheme(primary = ApexOrangeLight)

@Composable
fun ApexiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Android 12+ can tint the app with the user's wallpaper colors.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
