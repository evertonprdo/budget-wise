package com.evertonprdo.budgetwise.data.room.repositories.mapper

import com.evertonprdo.budgetwise.data.utils.DateHelper
import com.evertonprdo.budgetwise.data.utils.DateHelper.parseToUnix
import com.evertonprdo.budgetwise.model.Transaction
import com.evertonprdo.budgetwise.model.valueobjects.TransactionType
import com.evertonprdo.budgetwise.data.room.entities.Transaction as RoomTransaction

typealias A = RoomTransaction

object TransactionMapper {
    fun toDomain(transaction: RoomTransaction): Transaction {
        transaction.apply {
            return Transaction(
                id = id,
                type = TransactionType.getTypeFrom(type),
                cents = cents,
                date = DateHelper.parseUnixToDate(date),
                description = description,
                categoryId = categoryId,
                createdAt = createdAt.parseToDate(),
                updatedAt = updatedAt?.parseToDate()
            )
        }
    }

    fun toRoom(transaction: Transaction): RoomTransaction {
        transaction.apply {
            val uptAt = updatedAt?.parseToUnix()

            return RoomTransaction(
                id = id,
                type = type.ordinal,
                cents = cents,
                date = date.parseToUnix(),
                description = description,
                categoryId = categoryId,
                createdAt = createdAt.parseToUnix(),
                updatedAt = uptAt,
            )
        }
    }
}

private fun Long.parseToDate() = DateHelper.parseUnixToDate(this)