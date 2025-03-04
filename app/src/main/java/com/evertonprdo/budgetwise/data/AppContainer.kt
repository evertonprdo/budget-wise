package com.evertonprdo.budgetwise.data

import android.content.Context
import com.evertonprdo.budgetwise.data.facade.FacadeTransactionsWithCategoryRepository
import com.evertonprdo.budgetwise.data.local.LocalCategoriesRepository
import com.evertonprdo.budgetwise.data.repositories.CategoriesRepository
import com.evertonprdo.budgetwise.data.repositories.TransactionsRepository
import com.evertonprdo.budgetwise.data.repositories.TransactionsWithCategoryRepository
import com.evertonprdo.budgetwise.data.room.AppDatabase
import com.evertonprdo.budgetwise.data.room.repositories.RoomTransactionsRepository

interface AppContainer {
    val transactionsRepository: TransactionsRepository
    val categoriesRepository: CategoriesRepository
    val transactionsWithCategoriesRepository: TransactionsWithCategoryRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val transactionsRepository: TransactionsRepository by lazy {
        RoomTransactionsRepository(
            AppDatabase.getDatabase(context).transactionDao()
        )
    }

    override val categoriesRepository: CategoriesRepository =
        LocalCategoriesRepository()

    override val transactionsWithCategoriesRepository: TransactionsWithCategoryRepository by lazy {
        FacadeTransactionsWithCategoryRepository(transactionsRepository, categoriesRepository)
    }
}
