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
import com.sonukg97.fruitbasketapp.data.FruitsItem
import com.sonukg97.fruitbasketapp.ui.theme.FruitBasketAppTheme
import kotlinx.coroutines.launch
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FruitBasketAppTheme {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                       // NavigationDrawerExample()
                        NavigationDrawerExample(
                            onItemClick = {
                                scope.launch { drawerState.close() }
                            }
                        )
                    },
                    content = {
                        Scaffold(
                            topBar = {
                                TopAppBarExample(
                                    onMenuClick = {
                                        scope.launch { drawerState.open() }
                                    }
                                )
                            },
                            bottomBar = {
                                BottomNavigationExample()
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                val categories = listOf(
                                    "All", "Technology", "Sports", "Entertainment",
                                    "Business", "Health", "Science", "Politics"
                                )
                                var selectedCategory by remember { mutableStateOf<String?>(null) }
                                Column {
                                    CategoryChipsRow(
                                        categories = categories,
                                        selectedCategory = selectedCategory,
                                        onCategorySelected = { category ->
                                            selectedCategory = if (selectedCategory == category) null else category
                                        },
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                    LazyColumnItem()
                                    // Rest of your content
                                }
                            }
                        }
                    }
                )

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnItem(){
    var groupedItem= mapOf(
        "Fruits" to listOf(
            FruitsItem("Kiwi","Juicy tropical fruit",2.99,15,R.drawable.kiwi),
            FruitsItem("Tropical Kiwi","Juicy tropical fruit",2.99,10,R.drawable.kiwi1),
            FruitsItem("Pear","Tropical fruit",3.99,10,R.drawable.pear),
            FruitsItem("Banana","Regular fruit",1.99,18,R.drawable.banana),
            FruitsItem("Banana","Reguler fruit",1.99,5,R.drawable.banan)
        ),
        "Juicy Fruits" to listOf(
            FruitsItem("Cherry","Juicy tropical fruit",2.99,15,R.drawable.cherry),
            FruitsItem("Grapes","Juicy tropical fruit",2.99,10,R.drawable.grapes),
            FruitsItem("Lemon","Tropical fruit",3.99,10,R.drawable.lemon),
            FruitsItem("Pineapple","Regular fruit",1.99,18,R.drawable.pineapples),
            FruitsItem("Watermelon","Reguler fruit",1.99,5,R.drawable.watermelon)
        )
    )

    LazyColumn (modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)){
        groupedItem.forEach{(title,fruits)->
            run {
                items(fruits.size) { item ->
                    FruitCard(fruit = fruits[item])
                }
            }
        }
    }
}




@Composable
fun FruitCard(fruit: FruitsItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // Image at the top
            Image(
                painter = painterResource(id = fruit.img),
                contentDescription = fruit.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            // Content below image
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = fruit.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$${fruit.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = fruit.des,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Quantity indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${fruit.quantity} in stock",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

val sampleFruit = FruitsItem(
    name = "Kiwi",
    des = "Sweet and juicy tropical fruit",
    price = 2.99,
    quantity = 15,
    img = R.drawable.kiwi // Your drawable resource
)

@Preview
@Composable
fun FruitCardPreview() {
    MaterialTheme {
        FruitCard(fruit = sampleFruit)
    }
}

@Composable
fun CategoryChipsRow(
    categories: List<String>,
    modifier: Modifier = Modifier,
    selectedCategory: String? = null,
    onCategorySelected: (String) -> Unit = {}
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            CategoryChip(
                text = category,
                isSelected = category == selectedCategory,
                onClick = { onCategorySelected(category) }
            )
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color.Blue else Color.LightGray
    val contentColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(4.dp)
        )
    }
}
