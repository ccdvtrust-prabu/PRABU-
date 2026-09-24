package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = DeltaPrimaryDark,
    onPrimary = DeltaOnPrimaryDark,
    primaryContainer = DeltaPrimaryContainerDark,
    onPrimaryContainer = DeltaOnPrimaryContainerDark,
    secondary = DeltaSecondaryDark,
    onSecondary = DeltaOnSecondaryDark,
    secondaryContainer = DeltaSecondaryContainerDark,
    onSecondaryContainer = DeltaOnSecondaryContainerDark,
    tertiary = DeltaTertiaryDark,
    onTertiary = DeltaOnTertiaryDark,
    tertiaryContainer = DeltaTertiaryContainerDark,
    onTertiaryContainer = DeltaOnTertiaryContainerDark,
    background = DeltaBackgroundDark,
    onBackground = DeltaOnBackgroundDark,
    surface = DeltaSurfaceDark,
    onSurface = DeltaOnSurfaceDark,
    surfaceVariant = DeltaSurfaceVariantDark,
    onSurfaceVariant = DeltaOnSurfaceVariantDark,
    outline = DeltaOutline
  )

private val LightColorScheme =
  lightColorScheme(
    primary = DeltaPrimary,
    onPrimary = DeltaOnPrimary,
    primaryContainer = DeltaPrimaryContainer,
    onPrimaryContainer = DeltaOnPrimaryContainer,
    secondary = DeltaSecondary,
    onSecondary = DeltaOnSecondary,
    secondaryContainer = DeltaSecondaryContainer,
    onSecondaryContainer = DeltaOnSecondaryContainer,
    tertiary = DeltaTertiary,
    onTertiary = DeltaOnTertiary,
    tertiaryContainer = DeltaTertiaryContainer,
    onTertiaryContainer = DeltaOnTertiaryContainer,
    background = DeltaBackground,
    onBackground = DeltaOnBackground,
    surface = DeltaSurface,
    onSurface = DeltaOnSurface,
    surfaceVariant = DeltaSurfaceVariant,
    onSurfaceVariant = DeltaOnSurfaceVariant,
    outline = DeltaOutline
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Preserve brand identity blue-green theme
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

