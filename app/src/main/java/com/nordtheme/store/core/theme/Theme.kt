package com.nordtheme.store.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Immutable
data class NordPalette(
    val background: Color, val surfaceCard: Color, val surfacePill: Color,
    val textPrimary: Color, val textMuted: Color, val coralCta: Color,
    val badgeRed: Color, val glassTop: Color, val glassBottom: Color,
    val glassBorder: Color, val isDark: Boolean
)

val DarkNordPalette = NordPalette(
    Color(0xFF000000), Color(0xFF161618), Color(0xFF1E1E20), Color(0xFFFFFFFF),
    Color(0xFF8E8E93), Color(0xFFEE3B33), Color(0xFFFF3B30), Color(0xCC2A2A2E),
    Color(0xE61A1A1D), Color(0x33FFFFFF), true
)
val LightNordPalette = NordPalette(
    Color(0xFFF6F6F9), Color(0xFFFFFFFF), Color(0xFFEBEBF0), Color(0xFF111114),
    Color(0xFF6E6E73), Color(0xFFEE3B33), Color(0xFFFF3B30), Color(0xE6FFFFFF),
    Color(0xCCF2F2F7), Color(0x22000000), false
)
val LocalNordPalette = staticCompositionLocalOf { DarkNordPalette }

@Composable
fun NordThemeStoreTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val p = if (darkTheme) DarkNordPalette else LightNordPalette
    val cs = if (darkTheme) darkColorScheme(primary = p.coralCta, background = p.background, surface = p.surfaceCard)
             else lightColorScheme(primary = p.coralCta, background = p.background, surface = p.surfaceCard)
    CompositionLocalProvider(LocalNordPalette provides p) { MaterialTheme(colorScheme = cs, content = content) }
}
