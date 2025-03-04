package com.evertonprdo.budgetwise.data.local

import androidx.compose.ui.graphics.Color
import com.evertonprdo.budgetwise.R
import com.evertonprdo.budgetwise.data.repositories.CategoriesRepository
import com.evertonprdo.budgetwise.model.Category

class LocalCategoriesRepository : CategoriesRepository {

    override fun find(id: Int): Category =
        categories.first { it.id == id }

    override fun getAll(): List<Category> = categories

    companion object {

        val categories = listOf(
            Category(
                id = 1,
                name = "grocery-shopping",
                displayName = "Grocery Shopping",
                color = Color(0xFFad46ff),
                icon = R.drawable.building_store // "building-store"
            ),
            Category(
                id = 2,
                name = "recurring",
                displayName = "Recurring",
                color = Color(0xFF00bc7d),
                icon = R.drawable.refresh // "refresh"
            ),
            Category(
                id = 3,
                name = "one-time",
                displayName = "One-Time",
                color = Color(0xFF2b7fff),
                icon = R.drawable.receipt // "receipt"
            ),
            Category(
                id = 4,
                name = "utility-bills",
                displayName = "Utility Bills",
                color = Color(0xFFfe9a00),
                icon = R.drawable.bulb // "bulb"
            ),
            Category(
                id = 5,
                name = "miscellaneous",
                displayName = "Miscellaneous",
                color = Color(0xFF62748e),
                icon = R.drawable.puzzle // "puzzle"
            ),
        )
    }
}

