package com.evertonprdo.budgetwise.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: String,

    val type: Int,
    val cents: Long,
    val date: Long,
    val description: String,

    // @feature: categories management
    @ColumnInfo(name = "category_id")
    val categoryId: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis() / 1000,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long? = null,
)