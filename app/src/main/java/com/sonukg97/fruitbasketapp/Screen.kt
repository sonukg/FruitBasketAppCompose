package com.sonukg97.fruitbasketapp

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Search : Screen("search")
    object Fav : Screen("fav")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object About : Screen("about")

    object ItemDetails : Screen("item_details/{itemId}") {
        fun createRoute(itemId: String) = "item_details/$itemId"
    }
}
