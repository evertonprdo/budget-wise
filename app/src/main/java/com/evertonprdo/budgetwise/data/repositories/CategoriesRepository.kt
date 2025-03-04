package com.evertonprdo.budgetwise.data.repositories

import com.evertonprdo.budgetwise.model.Category

interface CategoriesRepository {
    fun find(id: Int): Category
    fun getAll(): List<Category>
}