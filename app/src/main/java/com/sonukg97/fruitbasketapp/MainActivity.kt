package com.sonukg97.fruitbasketapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sonukg97.fruitbasketapp.screens.AboutScreen
import com.sonukg97.fruitbasketapp.screens.SearchScreen
import com.sonukg97.fruitbasketapp.screens.CartScreen
import com.sonukg97.fruitbasketapp.screens.FavScreen
import com.sonukg97.fruitbasketapp.screens.HomeScreen
import com.sonukg97.fruitbasketapp.screens.ItemDetailsScreen
import com.sonukg97.fruitbasketapp.screens.ProfileScreen
import com.sonukg97.fruitbasketapp.screens.SettingScreen
import com.sonukg97.fruitbasketapp.ui.theme.FruitBasketAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FruitBasketAppTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        NavigationDrawerExample(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    },
                    content = {
                        Scaffold(
                            topBar = {
                                TopAppBarExample(
                                    onMenuClick = {
                                        scope.launch { drawerState.open() }
                                    },
                                    onProfileClick = { navController.navigate(Screen.Profile.route) },
                                    onSettingsClick = { navController.navigate(Screen.Settings.route) },
                                    onAboutClick = { navController.navigate(Screen.About.route) }
                                )
                            },
                            bottomBar = {
                                if (currentRoute != Screen.ItemDetails.route && currentRoute?.startsWith("item_details") != true) {
                                    BottomNavigationExample(navController = navController)
                                }
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                NavHost(
                                    navController = navController,
                                    startDestination = Screen.Home.route
                                ) {
                                    composable(Screen.Home.route) {
                                        HomeScreen(
                                            onItemClick = { itemName ->
                                                navController.navigate(Screen.ItemDetails.createRoute(itemName))
                                            }
                                        )
                                    }
                                    composable(Screen.Search.route) { SearchScreen() }
                                    composable(Screen.Fav.route) { FavScreen() }
                                    composable(Screen.Cart.route) { CartScreen() }
                                    composable(Screen.Profile.route) { ProfileScreen() }
                                    composable(Screen.Settings.route) { SettingScreen() }
                                    composable(Screen.About.route) { AboutScreen() }
                                    composable(
                                        route = Screen.ItemDetails.route,
                                        arguments = listOf(navArgument("itemId") { type = NavType.StringType })
                                    ) { backStackEntry ->
                                        val itemId = backStackEntry.arguments?.getString("itemId")
                                        ItemDetailsScreen(itemId = itemId)
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}


