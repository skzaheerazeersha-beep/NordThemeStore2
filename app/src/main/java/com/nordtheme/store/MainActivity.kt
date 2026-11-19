package com.nordtheme.store

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nordtheme.store.core.theme.*
import com.nordtheme.store.data.local.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(SystemBarStyle.dark(AndroidColor.TRANSPARENT), SystemBarStyle.dark(AndroidColor.TRANSPARENT))
        setContent {
            NordThemeStoreTheme(darkTheme = true) {
                val dao = remember { StoreDatabase.getInstance(LocalContext.current).storeDao() }
                LaunchedEffect(Unit) { if (dao.count() == 0) dao.insertAllIgnore(SeedCatalog.initialItems()) }
                val items by dao.observeAll().collectAsState(initial = emptyList())
                Box(Modifier.fillMaxSize().background(LocalNordPalette.current.background), Alignment.Center) {
                    Text("NordThemeStore2 • ${items.size} items", color = LocalNordPalette.current.textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
