package com.evertonprdo.budgetwise.data.facade

import com.evertonprdo.budgetwise.model.Category
import com.evertonprdo.budgetwise.model.Transaction
import com.evertonprdo.budgetwise.model.valueobjects.TransactionWithCategory

object TransactionWithCategoryMapper {
    fun toDomain(
        transaction: Transaction,
        category: Category
    ): TransactionWithCategory {
        transaction.apply {
            return TransactionWithCategory(
                id = id,
                type = type,
                cents = cents,
                date = date,
                description = description,
                category = category,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}