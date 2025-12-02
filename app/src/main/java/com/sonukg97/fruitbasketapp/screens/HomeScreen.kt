package com.sonukg97.fruitbasketapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sonukg97.fruitbasketapp.FruitCard
import com.sonukg97.fruitbasketapp.data.FruitsData
import androidx.compose.foundation.layout.fillMaxSize
@Composable
fun HomeScreen(onItemClick: (String) -> Unit) {
    val categories = listOf(
        "All", "Fruits", "Juice", "Vegetables", "Dry Fruits","Bakery","Groceries",
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
        LazyColumnItem(selectedCategory = selectedCategory, onItemClick = onItemClick)
    }
}

@Composable
fun LazyColumnItem(selectedCategory: String?, onItemClick: (String) -> Unit) {
    val groupedItem = FruitsData.groupedItem

    val filteredItems = if (selectedCategory == null || selectedCategory == "All") {
        groupedItem.values.flatten()
    } else {
        groupedItem[selectedCategory] ?: emptyList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filteredItems.size) { index ->
            FruitCard(
                fruit = filteredItems[index],
                modifier = Modifier.clickable { onItemClick(filteredItems[index].name) }
            )
        }
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


