package com.evertonprdo.budgetwise.model.valueobjects

import com.evertonprdo.budgetwise.model.Category
import com.evertonprdo.budgetwise.model.TransactionType
import java.util.Date

data class TransactionWithCategory(
    val id: Long,

    val type: TransactionType,
    val cents: Long,
    val date: Date,
    val description: String,
    val category: Category,

    val createdAt: Date,
    val updatedAt: Date? = null
)
