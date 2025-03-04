package com.evertonprdo.budgetwise.data.repositories

import com.evertonprdo.budgetwise.model.Category

interface CategoriesRepository {
    fun findCategory(id: Long): Category
}