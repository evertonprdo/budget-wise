package com.evertonprdo.budgetwise.data.facade

import com.evertonprdo.budgetwise.data.repositories.CategoriesRepository
import com.evertonprdo.budgetwise.data.repositories.TransactionsRepository
import com.evertonprdo.budgetwise.data.repositories.TransactionsWithCategoryRepository
import com.evertonprdo.budgetwise.model.valueobjects.TransactionWithCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FacadeTransactionsWithCategoryRepository(
    private val transactionsRepository: TransactionsRepository,
    private val categoriesRepository: CategoriesRepository
) : TransactionsWithCategoryRepository {

    override fun getAll(): Flow<List<TransactionWithCategory>> =
        transactionsRepository.getAll().map {
            it.map { transaction ->
                TransactionWithCategoryMapper.toDomain(
                    transaction,
                    categoriesRepository.find(transaction.categoryId)
                )
            }
        }
}