package com.evertonprdo.budgetwise.data.room.repositories

import com.evertonprdo.budgetwise.data.repositories.TransactionsRepository
import com.evertonprdo.budgetwise.data.room.dao.TransactionDao
import com.evertonprdo.budgetwise.data.room.repositories.mapper.TransactionMapper
import com.evertonprdo.budgetwise.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomTransactionsRepository(
    private val transactionDao: TransactionDao
) : TransactionsRepository {

    override fun getAll(): Flow<List<Transaction>> =
        transactionDao.getAll().map {
            it.map(TransactionMapper::toDomain)
        }

    override suspend fun create(transaction: Transaction) {
        transactionDao.insert(TransactionMapper.toRoom(transaction))
    }
}