package com.evertonprdo.budgetwise.model.valueobjects

import com.evertonprdo.budgetwise.model.Category
import java.util.Date

data class TransactionWithCategory(
    val id: String,

    val type: TransactionType,
    val cents: Long,
    val date: Date,
    val description: String,
    val category: Category,

    val createdAt: Date,
    val updatedAt: Date? = null
)
