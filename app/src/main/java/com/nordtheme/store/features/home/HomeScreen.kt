package com.nordtheme.store.features.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.nordtheme.store.core.theme.LocalNordPalette
import com.nordtheme.store.core.ui.*
import com.nordtheme.store.domain.model.*

private enum class HomeSubTab(val label: String, val searchHint: String) {
    Home("Home", "anime"), Themes("Themes", "love"), Font("Font", "font"),
    Wallpapers("Wallpapers", "love"), MotionWallpapers("Motion wallpapers", "moon"), VideoRingtones("Video ringtones", "pubg")
}

@Composable
fun HomeScreen(items: List<StoreItem>, onSearchClick: () -> Unit, onItemClick: (String) -> Unit, onSeeAllClick: (String, String) -> Unit) {
    val palette = LocalNordPalette.current
    var selectedTab by remember { mutableStateOf(HomeSubTab.Home) }
    val filteredItems = remember(items, selectedTab) {
        when (selectedTab) {
            HomeSubTab.Home -> items
            HomeSubTab.Themes -> items.filter { it.type == ItemType.THEME || it.type == ItemType.ICON_PACK }
            HomeSubTab.Font -> items.filter { it.type == ItemType.FONT }
            HomeSubTab.Wallpapers -> items.filter { it.type == ItemType.WALLPAPER }
            HomeSubTab.MotionWallpapers -> items.filter { it.type == ItemType.LIVE_WALLPAPER }
            HomeSubTab.VideoRingtones -> items.filter { it.type == ItemType.VIDEO_RINGTONE }
        }.ifEmpty { items }
    }
    val banners: List<HeroBannerUi> = remember(selectedTab) {
        when (selectedTab) {
            HomeSubTab.Home -> listOf(HeroBannerUi("h1", null, "18 Nov Update", "New Arrivals", Color(0xFF1F2D4D), Color(0xFF141E33), true), HeroBannerUi("h2", null, "Hot Topics Chart", "HOT", Color(0xFFFAD390), Color(0xFFF6B93B)))
            HomeSubTab.Themes -> listOf(HeroBannerUi("t1", "New", "18 Nov Update", "New Arrivals", Color(0xFFD4A36A), Color(0xFFB38148), true), HeroBannerUi("t2", null, "List of Rising Theme", "RISING", Color(0xFF81C7F5), Color(0xFF54A8E8)))
            HomeSubTab.Font -> listOf(HeroBannerUi("f1", "New", "18 Nov Update", "New Arrivals", Color(0xFF284B70), Color(0xFF1C3552), true), HeroBannerUi("f2", null, "List of Hot Fonts", "HOT", Color(0xFFD0B3FF), Color(0xFFA678F2)))
            HomeSubTab.Wallpapers -> listOf(HeroBannerUi("w1", "New", "18 Nov Update", "New Arrivals", Color(0xFFC4A79E), Color(0xFF9E7F76), true), HeroBannerUi("w2", null, "List of Rising", "RISING", Color(0xFFD8A7F5), Color(0xFFB878E8)))
            HomeSubTab.MotionWallpapers -> listOf(HeroBannerUi("m1", "New", "18 Nov Update", "New Arrivals", Color(0xFF10204A), Color(0xFF0A1430), true), HeroBannerUi("m2", null, "List of Rising", "RISING", Color(0xFFFBD285), Color(0xFFF5B041)))
            HomeSubTab.VideoRingtones -> listOf(HeroBannerUi("v1", null, "List of Rising", "RISING", Color(0xFFD4AEFC), Color(0xFFAC7AF5)), HeroBannerUi("v2", null, "List of New", "NEW", Color(0xFFB8E994), Color(0xFF78E08F)))
        }
    }
    val shortcuts: List<ShortcutUi> = remember(selectedTab) {
        when (selectedTab) {
            HomeSubTab.Home -> listOf(ShortcutUi("cute", "Cute", Icons.Outlined.Extension, Color(0xFFFF8C00)), ShortcutUi("cartoon", "Cartoon...", Icons.Outlined.Face, Color(0xFF29B6F6)), ShortcutUi("nature", "Nature", Icons.Outlined.Public, Color(0xFF7E57C2)), ShortcutUi("classic", "Classic", Icons.Outlined.BookmarkBorder, Color(0xFF42A5F5)))
            HomeSubTab.Font -> listOf(ShortcutUi("colorful", "colorful", Icons.Outlined.Palette, Color(0xFF7E57C2)), ShortcutUi("bw", "black&...", Icons.Outlined.TextFields, Color(0xFF29B6F6)), ShortcutUi("cute", "cute", Icons.Outlined.Face, Color(0xFF42A5F5)), ShortcutUi("curly", "curly", Icons.Outlined.AutoAwesome, Color(0xFFFFB300)))
            else -> listOf(ShortcutUi("nature", "Nature", Icons.Outlined.Public, Color(0xFF7E57C2)), ShortcutUi("animal", "Animal", Icons.Outlined.Pets, Color(0xFF29B6F6)), ShortcutUi("cute", "Cute", Icons.Outlined.Extension, Color(0xFFFF8C00)), ShortcutUi("classic", "Classic", Icons.Outlined.StarOutline, Color(0xFFFFB300)))
        }
    }
    val sectionTitle = when (selectedTab) { HomeSubTab.Font -> "Classic"; HomeSubTab.MotionWallpapers -> "Featured Live Wallpapers"; HomeSubTab.VideoRingtones -> "Audio-Visual Treat"; else -> "Trending" }
    LazyColumn(Modifier.fillMaxSize().background(palette.background), contentPadding = PaddingValues(top = 8.dp, bottom = 96.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { SearchPillBar(selectedTab.searchHint, onSearchClick, Modifier.padding(horizontal = 16.dp)) }
        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                items(HomeSubTab.entries) { tab ->
                    val sel = tab == selectedTab
                    Column(Modifier.clickable { selectedTab = tab }, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(tab.label, color = if (sel) palette.textPrimary else palette.textMuted, fontSize = 15.sp, fontWeight = if (sel) FontWeight.Bold else FontWeight.Normal)
                        Spacer(Modifier.height(6.dp))
                        Box(Modifier.width(28.dp).height(2.5.dp).background(if (sel) palette.textPrimary else Color.Transparent))
                    }
                }
            }
        }
        item { HeroBannerCarousel(banners) { b -> onSeeAllClick(b.title, b.title) } }
        item { CategoryShortcutRow(shortcuts) { s -> onSeeAllClick(s.label, s.id) } }
        item {
            Column {
                SectionHeader(sectionTitle) { onSeeAllClick(sectionTitle, selectedTab.label) }
                Spacer(Modifier.height(6.dp))
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(filteredItems, key = { it.id }) { item -> PortraitItemCard(item, { onItemClick(item.id) }) }
                }
            }
        }
        item { RankPromoRow({ onSeeAllClick("Top Ranked", "rank") }, { onSeeAllClick("Curated Packs", "featured") }) }
    }
}
