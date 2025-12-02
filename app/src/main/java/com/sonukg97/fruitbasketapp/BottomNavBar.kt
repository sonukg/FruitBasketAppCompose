package com.sonukg97.fruitbasketapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sonukg97.fruitbasketapp.ui.theme.FruitBasketAppTheme
import kotlin.to

@Composable
fun NavigationBarExample(){
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home","Search","Fav","Cart")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Favorite, Icons.Filled.ShoppingCart)
    val unSelectedIcons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Search,
        Icons.Outlined.Favorite,
        Icons.Outlined.ShoppingCart)
    NavigationBar() { items.forEachIndexed { index, item ->
        NavigationRailItem(
            label = { Text(item) },
            selected = selectedItem == index,
            onClick = { selectedItem = index},
            icon = {
                Icon(if (selectedItem == index) selectedIcons[index] else unSelectedIcons[index], contentDescription = item)
            }

        )
    } }

}

@Composable
fun BottomNavigationExample(navController: androidx.navigation.NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Fav,
        Screen.Cart
    )
    val icons = mapOf(
        Screen.Home to (Icons.Filled.Home to Icons.Outlined.Home),
        Screen.Search to (Icons.Filled.Search to Icons.Outlined.Search),
        Screen.Fav to (Icons.Filled.Favorite to Icons.Outlined.Favorite),
        Screen.Cart to (Icons.Filled.ShoppingCart to Icons.Outlined.ShoppingCart)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val (selectedIcon, unselectedIcon) = icons[screen]!!

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) selectedIcon else unselectedIcon,
                        contentDescription = screen.route
                    )
                },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}




