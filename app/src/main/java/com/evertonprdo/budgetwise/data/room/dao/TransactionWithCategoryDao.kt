package com.evertonprdo.budgetwise.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.evertonprdo.budgetwise.data.room.entities.relations.TransactionWithCategory
import kotlinx.coroutines.flow.Flow

// @feature: categories management
@Dao
interface TransactionWithCategoryDao {
    @Query(
        """
        SELECT 
            t.id as id,
            type,
            cents,
            date,
            description,
            category_id,
            created_at,
            updated_at,
            
            c.id as categoryId,
            name,
            display_name,
            color,
            icon_key
            
        FROM transactions as t
        JOIN categories as c ON t.category_id = c.id
    """
    )
    fun getAllWithCategory(): Flow<List<TransactionWithCategory>>
}