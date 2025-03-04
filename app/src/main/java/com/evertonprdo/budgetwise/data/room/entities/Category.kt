package com.evertonprdo.budgetwise.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    @ColumnInfo(name = "display_name")
    val displayName: String,

    val color: String,

    @ColumnInfo(name = "icon_key")
    val iconKey: String,
)