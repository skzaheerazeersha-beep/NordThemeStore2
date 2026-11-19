package com.nordtheme.store.features.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.nordtheme.store.core.theme.LocalNordPalette
import com.nordtheme.store.core.ui.RankPromoRow

@Composable
fun MeScreen(
    favoritesCount: Int, downloadsCount: Int,
    onSearchClick: () -> Unit, onDownloadsClick: () -> Unit,
    onFavoritesClick: () -> Unit, onSettingsClick: () -> Unit,
    onResourceClick: (String) -> Unit
) {
    val p = LocalNordPalette.current
    LazyColumn(Modifier.fillMaxSize().background(p.background), contentPadding = PaddingValues(top = 12.dp, bottom = 96.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), Arrangement.End) {
                TopCircleBtn(Icons.Outlined.Search, "Search", onSearchClick)
                Spacer(Modifier.width(10.dp))
                TopCircleBtn(Icons.Outlined.Download, "Downloads", onDownloadsClick)
                Spacer(Modifier.width(10.dp))
                TopCircleBtn(Icons.Outlined.Settings, "Settings", onSettingsClick)
            }
        }
        item {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(56.dp).clip(CircleShape).background(Color(0xFF262629)), Alignment.Center) { Icon(Icons.Outlined.Person, null, tint = Color.White, modifier = Modifier.size(30.dp)) }
                Spacer(Modifier.width(14.dp))
                Column { Text("OnePlus Nord 6", color = p.textPrimary, fontSize = 19.sp, fontWeight = FontWeight.Bold); Text("ColorOS 17.0 • $downloadsCount downloads", color = p.textMuted, fontSize = 13.sp) }
            }
        }
        item {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp).clip(RoundedCornerShape(16.dp)).background(p.surfaceCard).padding(vertical = 16.dp, horizontal = 8.dp), Arrangement.SpaceEvenly) {
                QuickActionItem(Icons.Outlined.MonetizationOn, "My coins") { onResourceClick("Coins") }
                QuickActionItem(Icons.Outlined.ReceiptLong, "Purchased") { onDownloadsClick() }
                QuickActionItem(Icons.Outlined.BookmarkBorder, "Favorites ($favoritesCount)") { onFavoritesClick() }
                QuickActionItem(Icons.Outlined.StarOutline, "Following") { onResourceClick("Following") }
            }
        }
        item {
            Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp).clip(RoundedCornerShape(16.dp)).background(p.surfaceCard).padding(16.dp)) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Text("My resources", color = p.textPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Text("All >", color = p.textMuted, fontSize = 13.sp, modifier = Modifier.clickable { onDownloadsClick() })
                }
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    ResourceBadge(Icons.Outlined.AutoFixHigh, "Themes", Color(0xFFFF3B30)) { onResourceClick("Themes") }
                    ResourceBadge(Icons.Outlined.TextFields, "Fonts", Color(0xFFFF8C00)) { onResourceClick("Font") }
                    ResourceBadge(Icons.Outlined.Wallpaper, "Wallpapers", Color(0xFFFFB300)) { onResourceClick("Wallpapers") }
                    ResourceBadge(Icons.Outlined.PlayCircleFilled, "Live\nwallpaper", Color(0xFF007AFF)) { onResourceClick("Motion wallpapers") }
                }
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.Start) {
                    ResourceBadge(Icons.Outlined.Notifications, "Video\nringtone", Color(0xFFFF453A)) { onResourceClick("Video ringtones") }
                    Spacer(Modifier.width(28.dp))
                    ResourceBadge(Icons.Outlined.Widgets, "Icon packs", Color(0xFF34C759)) { onResourceClick("Icons") }
                }
            }
        }
        item {
            Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp).clip(RoundedCornerShape(16.dp)).background(p.surfaceCard).padding(16.dp)) {
                Text("My services", color = p.textPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                    QuickActionItem(Icons.Outlined.MonetizationOn, "Orders") { onDownloadsClick() }
                    QuickActionItem(Icons.Outlined.ConfirmationNumber, "Coupons") { onResourceClick("Coupons") }
                    QuickActionItem(Icons.Outlined.HeadsetMic, "Support") { onSettingsClick() }
                }
            }
        }
        item { RankPromoRow({ onResourceClick("Top Ranked") }, { onResourceClick("Collections") }) }
    }
}

@Composable private fun TopCircleBtn(icon: ImageVector, desc: String, onClick: () -> Unit) {
    val p = LocalNordPalette.current
    Box(Modifier.size(38.dp).clip(CircleShape).background(p.surfaceCard).clickable(onClick = onClick), Alignment.Center) { Icon(icon, desc, tint = p.textPrimary, modifier = Modifier.size(20.dp)) }
}
@Composable private fun QuickActionItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    val p = LocalNordPalette.current
    Column(Modifier.clickable(onClick = onClick).padding(horizontal = 6.dp), horizontalAlignment = Alignment.CenterHorizontally) { Icon(icon, label, tint = p.textPrimary, modifier = Modifier.size(22.dp)); Spacer(Modifier.height(6.dp)); Text(label, color = p.textMuted, fontSize = 11.sp, textAlign = TextAlign.Center) }
}
@Composable private fun ResourceBadge(icon: ImageVector, label: String, bg: Color, onClick: () -> Unit) {
    val p = LocalNordPalette.current
    Column(Modifier.width(72.dp).clickable(onClick = onClick), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(44.dp).clip(CircleShape).background(bg), Alignment.Center) { Icon(icon, label, tint = Color.White, modifier = Modifier.size(22.dp)) }
        Spacer(Modifier.height(6.dp)); Text(label, color = p.textPrimary, fontSize = 11.sp, lineHeight = 14.sp, textAlign = TextAlign.Center)
    }
}
