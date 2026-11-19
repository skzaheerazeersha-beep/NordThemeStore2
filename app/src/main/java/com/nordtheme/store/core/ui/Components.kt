package com.nordtheme.store.core.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.nordtheme.store.core.theme.LocalNordPalette
import com.nordtheme.store.domain.model.*

@Composable
fun SearchPillBar(hint: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val p = LocalNordPalette.current
    Row(modifier.fillMaxWidth().height(44.dp).clip(RoundedCornerShape(50)).background(p.surfacePill).clickable(onClick = onClick).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Search, "Search", tint = p.textMuted, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Text(hint, color = p.textMuted, fontSize = 15.sp, maxLines = 1)
    }
}

data class HeroBannerUi(val id: String, val badge: String?, val subtitle: String, val title: String, val topColor: Color, val bottomColor: Color, val isPhoneMockup: Boolean = false)

@Composable
fun HeroBannerCarousel(banners: List<HeroBannerUi>, onBannerClick: (HeroBannerUi) -> Unit) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(banners, key = { it.id }) { b ->
            Box(Modifier.width(172.dp).height(228.dp).clip(RoundedCornerShape(16.dp)).background(Brush.verticalGradient(listOf(b.topColor, b.bottomColor))).clickable { onBannerClick(b) }.padding(12.dp)) {
                if (b.isPhoneMockup) {
                    Box(Modifier.align(Alignment.TopCenter).padding(top = 6.dp).width(106.dp).height(148.dp).clip(RoundedCornerShape(16.dp)).background(Brush.linearGradient(listOf(Color(0xFF1A2238), b.topColor, Color(0xFF6B4E9B)))).border(2.dp, Color(0xFF0E0E10), RoundedCornerShape(16.dp))) {
                        Text("10:00", color = Color.White.copy(alpha = 0.85f), fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.TopCenter).padding(top = 18.dp))
                    }
                } else {
                    Box(Modifier.align(Alignment.Center).padding(bottom = 26.dp).size(96.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.22f)), Alignment.Center) {
                        Text(b.title.take(3).uppercase(), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
                b.badge?.let { Box(Modifier.align(Alignment.TopStart).clip(RoundedCornerShape(6.dp)).background(Color(0x99222226)).padding(horizontal = 7.dp, vertical = 2.dp)) { Text(it, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.SemiBold) } }
                Column(Modifier.align(Alignment.BottomStart)) { Text(b.subtitle, color = Color.White.copy(alpha = 0.88f), fontSize = 11.sp, maxLines = 1); Text(b.title, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold, maxLines = 1) }
            }
        }
    }
}

data class ShortcutUi(val id: String, val label: String, val icon: ImageVector, val tint: Color)

@Composable
fun CategoryShortcutRow(shortcuts: List<ShortcutUi>, onShortcutClick: (ShortcutUi) -> Unit) {
    val p = LocalNordPalette.current
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        shortcuts.take(4).forEach { s ->
            Column(Modifier.weight(1f).height(74.dp).clip(RoundedCornerShape(12.dp)).background(p.surfaceCard).clickable { onShortcutClick(s) }.padding(6.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(s.icon, s.label, tint = s.tint, modifier = Modifier.size(22.dp))
                Spacer(Modifier.height(6.dp))
                Text(s.label, color = p.textPrimary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, onSeeAll: () -> Unit, modifier: Modifier = Modifier) {
    val p = LocalNordPalette.current
    Row(modifier.fillMaxWidth().clickable(onClick = onSeeAll).padding(horizontal = 16.dp, vertical = 6.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        Text(title, color = p.textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "See all", tint = p.textMuted, modifier = Modifier.size(20.dp))
    }
}

@Composable
fun PortraitItemCard(item: StoreItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val p = LocalNordPalette.current
    Column(modifier.width(114.dp).clickable(onClick = onClick)) {
        Box(Modifier.fillMaxWidth().aspectRatio(0.56f).clip(RoundedCornerShape(10.dp)).background(Brush.verticalGradient(listOf(Color(item.accentHex), Color(item.secondaryHex)))).padding(8.dp)) {
            if (item.type == ItemType.FONT) {
                Column(Modifier.fillMaxSize().padding(top = 18.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(item.title.take(14), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, maxLines = 2)
                    Spacer(Modifier.height(6.dp))
                    Text("Aa Bb Cc", color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp)
                }
            } else {
                Text("10:00", color = Color.White.copy(alpha = 0.9f), fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.TopCenter).padding(top = 22.dp))
            }
            item.type.pillTag?.let { pill ->
                Box(Modifier.align(Alignment.TopStart).clip(RoundedCornerShape(5.dp)).background(Color(0x991C1C1E)).padding(horizontal = 6.dp, vertical = 2.dp)) { Text(pill, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Medium) }
            }
            if (!item.isFree) {
                Box(Modifier.align(Alignment.BottomEnd).clip(RoundedCornerShape(5.dp)).background(p.coralCta).padding(horizontal = 5.dp, vertical = 2.dp)) { Text("₹${item.priceInr.toInt()}", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold) }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(item.title, color = p.textPrimary, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun RankPromoRow(onRankClick: () -> Unit, onFeaturedClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(Modifier.weight(1f).height(82.dp).clip(RoundedCornerShape(14.dp)).background(Brush.linearGradient(listOf(Color(0xFF2855A8), Color(0xFF5386E4)))).clickable(onClick = onRankClick).padding(14.dp), Alignment.CenterStart) {
            Text("RANK", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        }
        Box(Modifier.weight(1f).height(82.dp).clip(RoundedCornerShape(14.dp)).background(Brush.linearGradient(listOf(Color(0xFF1B3B48), Color(0xFF457B6C)))).clickable(onClick = onFeaturedClick).padding(14.dp), Alignment.CenterStart) {
            Column { Text("Collections", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold); Text("Curated packs", color = Color.White.copy(alpha = 0.78f), fontSize = 11.sp) }
        }
    }
}
