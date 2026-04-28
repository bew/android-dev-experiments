// WHAT:
// The MyEnergyBarTheme composable that wraps all app UI.
// Uses Material3 defaults with dynamic color (Material You) on Android 12+, plain Material3
// defaults on older devices.
//
// NEEDED: Yes.
// This is what applies Material3 styling throughout the entire app.
//
// MAINTAINANCE:
// To add brand colors, define color schemes here directly.
// To disable dynamic color (always use fixed colors), set `dynamicColor = false`.

package dev.bew.myenergybar.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun MyEnergyBarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> if (darkTheme) MaterialTheme.colorScheme else MaterialTheme.colorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
