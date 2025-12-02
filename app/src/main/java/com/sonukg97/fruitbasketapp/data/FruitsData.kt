package com.sonukg97.fruitbasketapp.data

import com.sonukg97.fruitbasketapp.R

object FruitsData {
    val groupedItem = mapOf(
        "Fruits" to listOf(
            FruitsItem("Kiwi", "Juicy tropical fruit", 2.99, 15, R.drawable.kiwi),
            FruitsItem("Tropical Kiwi", "Juicy tropical fruit", 2.99, 10, R.drawable.kiwi1),
            FruitsItem("Pear", "Tropical fruit", 3.99, 10, R.drawable.pear),
            FruitsItem("Banana", "Regular fruit", 1.99, 18, R.drawable.banana),
            FruitsItem("Banana", "Reguler fruit", 1.99, 5, R.drawable.banan),
            FruitsItem("Cherry", "Juicy tropical fruit", 2.99, 15, R.drawable.cherry),
            FruitsItem("Grapes", "Juicy tropical fruit", 2.99, 10, R.drawable.grapes),
            FruitsItem("Lemon", "Tropical fruit", 3.99, 10, R.drawable.lemon),
            FruitsItem("Pineapple", "Regular fruit", 1.99, 18, R.drawable.pineapples),
            FruitsItem("Watermelon", "Reguler fruit", 1.99, 5, R.drawable.watermelon)
        ),
        "Vegetables" to listOf(
            // Reusing images for demo purposes as I don't have vegetable assets
            FruitsItem("Carrot", "Fresh organic carrot", 0.99, 20, R.drawable.kiwi),
            FruitsItem("Broccoli", "Green broccoli", 1.49, 15, R.drawable.pear),
            FruitsItem("Spinach", "Healthy spinach", 1.99, 10, R.drawable.banana)
        ),
        "Groceries" to listOf(
             FruitsItem("Rice", "Basmati Rice", 5.99, 50, R.drawable.kiwi),
             FruitsItem("Wheat", "Whole Wheat", 4.99, 40, R.drawable.pear)
        ),
        "Cleaning" to emptyList(),
        "Kitchen Tools" to emptyList(),
        "Washing" to emptyList(),
        "Home" to emptyList()
    )

    fun getFruitByName(name: String): FruitsItem? {
        groupedItem.values.forEach { list ->
            val fruit = list.find { it.name == name }
            if (fruit != null) return fruit
        }
        return null
    }
}
