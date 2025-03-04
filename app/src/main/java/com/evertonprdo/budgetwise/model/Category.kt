package com.evertonprdo.budgetwise.model

import androidx.annotation.DrawableRes

data class Category(
    val id: Long,

    val name: String,
    val displayName: String,
    val color: String,

    @DrawableRes val icon: Int
)
