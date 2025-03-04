package com.evertonprdo.budgetwise.data.repositories

import com.evertonprdo.budgetwise.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    fun getAll(): Flow<List<Transaction>>
    suspend fun create(transaction: Transaction)
}