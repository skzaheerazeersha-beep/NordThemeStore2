package com.nordtheme.store.data.local

import com.nordtheme.store.domain.model.ItemType

object SeedCatalog {
    fun initialItems(): List<StoreItemEntity> = listOf(
        StoreItemEntity("theme_minimal", "Minimal Color Bars", "Clean geometric lockscreen & icons", ItemType.THEME.name, "Classic", "Black", 0xFF1A2530, 0xFFE67E22, "StudioNord", 0.0, 0.0, 18.4, 14200, 620, true, true, false, false, false, true),
        StoreItemEntity("theme_raster", "Raster Full of Tenderness", "The speed of cherry blossoms falling is the frequency of you", ItemType.THEME.name, "Cute", "Pink", 0xFFF3E5EC, 0xFFE86C8D, "MeowDesign", 50.0, 100.0, 24.8, 8900, 66, false, true, true, false, false, false),
        StoreItemEntity("theme_bamboo", "Aesthetic Ancient Bamboo", "Warm sunlit bamboo & glass clock widget", ItemType.THEME.name, "Nature", "Green", 0xFF2D4A35, 0xFFC8D59C, "ZenCraft", 0.0, 0.0, 21.0, 11300, 412, true, true, false, false, false, true),
        StoreItemEntity("font_intima", "Intima Politha", "Playful vintage circus script", ItemType.FONT.name, "colorful", "Yellow", 0xFFF39C12, 0xFFFFF3E0, "TypeLab", 0.0, 0.0, 1.8, 9500, 290, true, true, false, false, false, true),
        StoreItemEntity("font_diary", "Goodnight Diary", "Soft handwritten journal font", ItemType.FONT.name, "cute", "Pink", 0xFFF9D7DE, 0xFF3A282D, "TypeLab", 29.0, 59.0, 1.4, 7200, 188, false, true, true, false, false, false),
        StoreItemEntity("wall_chile", "Chile Clouds", "Crisp 1.5K Patagonia twilight", ItemType.WALLPAPER.name, "Nature", "Blue", 0xFF28587B, 0xFFE5A38C, "EarthLens", 0.0, 0.0, 6.2, 21400, 910, true, true, false, false, false, true),
        StoreItemEntity("wall_dragonfly", "Dragonfly Resting", "Minimalist botanical illustration", ItemType.WALLPAPER.name, "Organism", "Green", 0xFF5FA777, 0xFFDCE8B5, "Botanica", 0.0, 0.0, 4.9, 15800, 540, false, true, true, false, false, true),
        StoreItemEntity("live_maple", "Garden Maple", "165Hz smooth autumn leaf breeze", ItemType.LIVE_WALLPAPER.name, "Nature", "Yellow", 0xFF7D6608, 0xFFF4D03F, "MotionLab", 0.0, 0.0, 14.5, 11200, 430, true, true, false, false, false, true),
        StoreItemEntity("live_cat", "Cute Orange Cat", "Interactive moonlit desk kitty", ItemType.LIVE_WALLPAPER.name, "Animal", "Colourful", 0xFF5D4B3E, 0xFFD9C3B0, "PawsMotion", 40.0, 80.0, 16.2, 9800, 720, true, true, true, false, false, false),
        StoreItemEntity("ring_treat", "Audio-Visual Treat", "Vibrant 3D animated call screen", ItemType.VIDEO_RINGTONE.name, "Illustration", "Purple", 0xFF3E1F5C, 0xFFE91E63, "PulseStudio", 0.0, 0.0, 19.0, 8700, 315, true, true, false, false, false, true),
        StoreItemEntity("icon_glass", "ColorOS Glass Icons", "Squircle frosted adaptive icon pack", ItemType.ICON_PACK.name, "Classic", "Colourful", 0xFF1F2B3E, 0xFF4CA1AF, "IconForge", 0.0, 0.0, 11.4, 13600, 490, true, true, true, false, false, true)
    )
}
