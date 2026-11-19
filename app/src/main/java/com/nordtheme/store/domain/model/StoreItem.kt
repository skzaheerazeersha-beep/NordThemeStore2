package com.nordtheme.store.domain.model

enum class ItemType(val label: String, val pillTag: String?) {
    THEME("Themes", null),
    FONT("Font", "font"),
    WALLPAPER("Wallpapers", null),
    LIVE_WALLPAPER("Motion wallpapers", "live wallpaper"),
    VIDEO_RINGTONE("Video ringtones", "video ringtone"),
    ICON_PACK("Icons", "icon pack")
}

data class StoreItem(
    val id: String, val title: String, val subtitle: String, val type: ItemType,
    val category: String, val colorTag: String, val accentHex: Long, val secondaryHex: Long,
    val creatorName: String, val priceInr: Double, val originalPriceInr: Double,
    val fileSizeMb: Double, val downloadsCount: Int, val favoritesCount: Int,
    val isNew: Boolean = false, val isTrending: Boolean = false, val isRising: Boolean = false,
    val isFavorite: Boolean = false, val isDownloaded: Boolean = false, val isPurchased: Boolean = false
) { val isFree: Boolean get() = priceInr <= 0.0 }

data class SearchTrend(val rank: Int, val keyword: String, val metricLabel: String)
