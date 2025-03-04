package com.evertonprdo.budgetwise.data.local

import com.evertonprdo.budgetwise.data.room.entities.Category

object LocalCategory {
    val categories = listOf(
        Category(
            id = 1,
            name = "grocery-shopping",
            displayName = "Grocery Shopping",
            color = "#ad46ff",
            iconKey = "building-store"
        ),
        Category(
            id = 2,
            name = "recurring",
            displayName = "Recurring",
            color = "#00bc7d",
            iconKey = "refresh"
        ),
        Category(
            id = 3,
            name = "one-time",
            displayName = "One-Time",
            color = "#2b7fff",
            iconKey = "receipt"
        ),
        Category(
            id = 4,
            name = "utility-bills",
            displayName = "Utility Bills",
            color = "#fe9a00",
            iconKey = "bulb"
        ),
        Category(
            id = 5,
            name = "miscellaneous",
            displayName = "Miscellaneous",
            color = "#62748e",
            iconKey = "puzzle"
        ),
    )
}