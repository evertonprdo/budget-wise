package com.evertonprdo.budgetwise.model.valueobjects

enum class TransactionType(displayName: String) {
    INCOME(displayName = "income"),
    OUTCOME(displayName = "outcome");

    companion object {
        fun getTypeFrom(ordinal: Int): TransactionType = when (ordinal) {
            0 -> INCOME
            1 -> OUTCOME
            else -> throw IllegalArgumentException()
        }
    }
}

fun a() {
    TransactionType.entries[0]
}