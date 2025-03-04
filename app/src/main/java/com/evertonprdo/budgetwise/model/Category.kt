package com.evertonprdo.budgetwise.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.evertonprdo.budgetwise.R

data class Category(
    val id: Int,

    val name: String,
    val displayName: String,
    val color: Color,

    @DrawableRes val icon: Int
) {

    companion object {
        val DEFAULT_CATEGORY = Category(
            id = 0,
            name = "transaction",
            displayName = "Transaction",
            color = Color(0xFF7722BB),
            icon = R.drawable.list_details
        )
    }
}
