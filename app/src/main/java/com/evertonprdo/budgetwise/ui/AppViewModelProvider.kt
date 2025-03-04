package com.evertonprdo.budgetwise.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evertonprdo.budgetwise.BudgetWiseApplication
import com.evertonprdo.budgetwise.ui.screens.transaction.EntryScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val appContainer = flightApplication().container

            EntryScreenViewModel(
                appContainer.transactionsRepository,
                appContainer.categoriesRepository
            )
        }
    }
}

fun CreationExtras.flightApplication(): BudgetWiseApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BudgetWiseApplication)