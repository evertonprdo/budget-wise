package com.evertonprdo.budgetwise.data.repositories

import com.evertonprdo.budgetwise.model.valueobjects.TransactionWithCategory
import kotlinx.coroutines.flow.Flow

interface TransactionsWithCategoryRepository {
    fun getAll(): Flow<List<TransactionWithCategory>>
}