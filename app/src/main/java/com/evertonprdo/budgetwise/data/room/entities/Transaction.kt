package com.evertonprdo.budgetwise.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val type: Int,
    val cents: Long,
    val date: Long,
    val description: String,

    // @feature: management categories
    @ColumnInfo(name = "category_id")
    val categoryId: Long,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis() / 1000,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long? = null,
)