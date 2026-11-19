package com.nordtheme.store.features.omg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.nordtheme.store.core.theme.LocalNordPalette
import com.nordtheme.store.core.ui.*
import com.nordtheme.store.domain.model.StoreItem

@Composable
fun ExploreScreen(items: List<StoreItem>, onItemClick: (String) -> Unit, onSeeAllClick: (String, String) -> Unit) {
    val p = LocalNordPalette.current
    val sections = listOf("Selection" to items, "Animals" to items.reversed(), "Lovely" to items.sortedByDescending { it.favoritesCount })
    LazyColumn(Modifier.fillMaxSize().background(p.background), contentPadding = PaddingValues(top = 14.dp, bottom = 96.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { Text("Explore", color = p.textPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp)) }
        sections.forEach { (title, list) ->
            item(key = title) {
                Column {
                    SectionHeader(title) { onSeeAllClick(title, title) }
                    Spacer(Modifier.height(6.dp))
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(list, key = { "${title}_${it.id}" }) { PortraitItemCard(it, { onItemClick(it.id) }) }
                    }
                }
            }
        }
    }
}
