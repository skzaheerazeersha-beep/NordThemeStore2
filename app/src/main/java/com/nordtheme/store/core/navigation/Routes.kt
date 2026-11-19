package com.nordtheme.store.core.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Category : Routes("category")
    data object OMG : Routes("omg")
    data object Me : Routes("me")
    data object Search : Routes("search")
    data object Downloads : Routes("downloads")
    data object Favorites : Routes("favorites")
    data object Settings : Routes("settings")
    data object Detail : Routes("detail/{itemId}") { fun create(id: String) = "detail/$id" }
    data object CategoryGrid : Routes("category_grid/{title}/{filterKey}") { fun create(t: String, k: String) = "category_grid/$t/$k" }
}
