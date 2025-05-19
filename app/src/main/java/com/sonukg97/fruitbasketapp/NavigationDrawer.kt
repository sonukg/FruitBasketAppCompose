package com.sonukg97.fruitbasketapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonukg97.fruitbasketapp.ui.theme.FruitBasketAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample(onMenuClick: () -> Unit) {
    TopAppBar(
        //windowInsets = AppBarDefaults.windowInsets,
        title = { Text("Fruit Basket") },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = {
                //NavigationDrawerExample()
                DrawerValue.Open
            }) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
            // Navigation icon
        },
        actions = {
            IconButton(onClick = {}) {
                //Icon(Icons.Filled.Favorite, contentDescription = null)
               // Icon(Icons.Default.Search, contentDescription = null)
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
            // Actions
        },
    )

}

@Composable
fun NavigationDrawerExample(onItemClick: () -> Unit = {}) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // icons to mimic drawer destinations
    val items =
        listOf(
            Icons.Default.Home,
            Icons.Default.AccountCircle,
            Icons.Default.Email,
            Icons.Default.Email,
            Icons.Default.Favorite,

        )
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
        ModalDrawerSheet() {
            Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                Spacer( modifier = Modifier.height(12.dp))
                items.forEach { item ->
                NavigationDrawerItem(
                    icon = { Icon(item, contentDescription = null) },
                    label = { Text(item.name.substringAfterLast(".")) },
                    selected = item == selectedItem.value,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                }
            }
        }
    },
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                //Spacer(Modifier.height(20.dp))
                //Button(onClick = { scope.launch { drawerState.open() } }) { Text("Click to open") }
            }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewTopAppBarExample() {
    FruitBasketAppTheme  {

        NavigationDrawerExample()

    }
}