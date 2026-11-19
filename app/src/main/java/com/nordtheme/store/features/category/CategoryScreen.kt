package com.nordtheme.store.features.category

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.nordtheme.store.core.theme.LocalNordPalette
import com.nordtheme.store.core.ui.SearchPillBar

private data class ColorTile(val label: String, val top: Color, val bottom: Color, val icon: ImageVector)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryScreen(onSearchClick: () -> Unit, onCategoryClick: (String, String) -> Unit) {
    val palette = LocalNordPalette.current
    var activeSection by remember { mutableStateOf("Themes") }
    val colorTiles = remember {
        listOf(
            ColorTile("White", Color(0xFFD5DCE4), Color(0xFF9EA9B6), Icons.Outlined.Diamond),
            ColorTile("Black", Color(0xFF42454B), Color(0xFF1F2124), Icons.Outlined.School),
            ColorTile("Yellow", Color(0xFFFFE57F), Color(0xFFFFCA28), Icons.Outlined.WbSunny),
            ColorTile("Red", Color(0xFFFFAB91), Color(0xFFFF7043), Icons.Outlined.FavoriteBorder),
            ColorTile("Purple", Color(0xFFD1B3FF), Color(0xFF9575CD), Icons.Outlined.FilterVintage),
            ColorTile("Blue", Color(0xFF81D4FA), Color(0xFF29B6F6), Icons.Outlined.Public),
            ColorTile("Green", Color(0xFF80E2A7), Color(0xFF2ECC71), Icons.Outlined.Eco),
            ColorTile("Pink", Color(0xFFFFB2D1), Color(0xFFFF80AB), Icons.Outlined.LocalFlorist),
            ColorTile("Colourful", Color(0xFFA7FFEB), Color(0xFF82B1FF), Icons.Outlined.Palette)
        )
    }
    val tagChips = listOf("iphone" to Color(0xFF5C6BC0), "love" to Color(0xFF66BB6A), "anime" to Color(0xFFFFB300), "car" to Color(0xFFE65100), "god" to Color(0xFFE53935), "panda" to Color(0xFF29B6F6), "dark" to Color(0xFF7E57C2), "ios" to Color(0xFF26C6DA), "black" to Color(0xFF9CCC65), "cute" to Color(0xFFFFCA28))
    LazyColumn(Modifier.fillMaxSize().background(palette.background), contentPadding = PaddingValues(16.dp, 8.dp, 16.dp, 96.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { SearchPillBar("anime", onSearchClick) }
        item {
            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                listOf("Themes", "Font").forEach { tab ->
                    val sel = tab == activeSection
                    Column(Modifier.padding(horizontal = 24.dp).clickable { activeSection = tab }, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(tab, color = if (sel) palette.textPrimary else palette.textMuted, fontSize = 16.sp, fontWeight = if (sel) FontWeight.Bold else FontWeight.Normal)
                        Spacer(Modifier.height(6.dp))
                        Box(Modifier.width(36.dp).height(2.5.dp).background(if (sel) palette.textPrimary else Color.Transparent))
                    }
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                RankTile("TRENDING", Color(0xFFFF5F4D), Color(0xFFE53935), Icons.Outlined.LocalFireDepartment, Modifier.weight(1f)) { onCategoryClick("Trending $activeSection", "trending") }
                RankTile("TOP", Color(0xFFFFAE2B), Color(0xFFF57C00), Icons.Outlined.BarChart, Modifier.weight(1f)) { onCategoryClick("Top $activeSection", "top") }
                RankTile("NEW", Color(0xFF4CA9FF), Color(0xFF1E88E5), Icons.Outlined.AutoAwesome, Modifier.weight(1f)) { onCategoryClick("New $activeSection", "new") }
            }
        }
        item {
            Text("Colour", color = palette.textPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                colorTiles.chunked(3).forEach { row ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { t ->
                            Box(Modifier.weight(1f).aspectRatio(1.05f).clip(RoundedCornerShape(12.dp)).background(Brush.verticalGradient(listOf(t.top, t.bottom))).clickable { onCategoryClick(t.label, t.label) }.padding(10.dp)) {
                                Icon(t.icon, null, tint = Color.White.copy(alpha = 0.28f), modifier = Modifier.size(54.dp).align(Alignment.TopEnd))
                                Text(t.label, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.BottomStart))
                            }
                        }
                    }
                }
            }
        }
        item {
            Text("$activeSection you may like", color = palette.textPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                tagChips.forEach { (lbl, tint) ->
                    Box(Modifier.clip(RoundedCornerShape(8.dp)).background(tint.copy(alpha = 0.18f)).clickable { onCategoryClick(lbl, lbl) }.padding(14.dp, 8.dp)) {
                        Text(lbl, color = tint, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}

@Composable
private fun RankTile(title: String, top: Color, bottom: Color, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier.aspectRatio(1f).clip(RoundedCornerShape(12.dp)).background(Brush.verticalGradient(listOf(top, bottom))).clickable(onClick = onClick).padding(10.dp)) {
        Icon(icon, null, tint = Color.White.copy(alpha = 0.45f), modifier = Modifier.size(22.dp).align(Alignment.TopEnd))
        Text(title, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.align(Alignment.BottomStart))
    }
}
