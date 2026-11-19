package com.nordtheme.store.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nordtheme.store.domain.model.ItemType
import com.nordtheme.store.domain.model.StoreItem

@Entity(tableName = "store_items")
data class StoreItemEntity(
    @PrimaryKey val id: String, val title: String, val subtitle: String, val typeName: String,
    val category: String, val colorTag: String, val accentHex: Long, val secondaryHex: Long,
    val creatorName: String, val priceInr: Double, val originalPriceInr: Double,
    val fileSizeMb: Double, val downloadsCount: Int, val favoritesCount: Int,
    val isNew: Boolean, val isTrending: Boolean, val isRising: Boolean,
    val isFavorite: Boolean, val isDownloaded: Boolean, val isPurchased: Boolean,
    val localFilePath: String? = null, val downloadedAt: Long = 0L
) {
    fun toDomain() = StoreItem(
        id, title, subtitle, ItemType.entries.firstOrNull { it.name == typeName } ?: ItemType.THEME,
        category, colorTag, accentHex, secondaryHex, creatorName, priceInr, originalPriceInr,
        fileSizeMb, downloadsCount, favoritesCount, isNew, isTrending, isRising,
        isFavorite, isDownloaded, isPurchased
    )
}

@Entity(tableName = "recent_searches")
data class RecentSearchEntity(@PrimaryKey val query: String, val timestamp: Long)
