package com.evertonprdo.budgetwise.data.room.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.evertonprdo.budgetwise.data.room.entities.Category
import com.evertonprdo.budgetwise.data.room.entities.Transaction

// @feature: categories management
data class TransactionWithCategory(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val transaction: Transaction
)