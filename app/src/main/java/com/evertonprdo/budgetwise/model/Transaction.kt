package com.evertonprdo.budgetwise.model

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