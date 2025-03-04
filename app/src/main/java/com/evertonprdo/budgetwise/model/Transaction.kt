package com.evertonprdo.budgetwise.model

import com.evertonprdo.budgetwise.model.valueobjects.TransactionType
import java.util.Date

data class Transaction(
    val id: Long,

    val type: TransactionType,
    val cents: Long,
    val date: Date,
    val description: String,
    val categoryId: Long,

    val createdAt: Date,
    val updatedAt: Date? = null
)