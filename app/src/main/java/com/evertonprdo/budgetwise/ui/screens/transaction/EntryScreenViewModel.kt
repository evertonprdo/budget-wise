package com.evertonprdo.budgetwise.ui.screens.transaction

import androidx.lifecycle.ViewModel
import com.evertonprdo.budgetwise.data.repositories.CategoriesRepository
import com.evertonprdo.budgetwise.data.repositories.TransactionsRepository
import com.evertonprdo.budgetwise.model.Category
import com.evertonprdo.budgetwise.model.Transaction
import com.evertonprdo.budgetwise.model.valueobjects.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class EntryScreenViewModel(
    private val transactionsRepository: TransactionsRepository,
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(TransactionUiState())
    val transactionUiState: StateFlow<TransactionUiState> =
        _uiState.asStateFlow()

    init {
        _uiState.value =
            TransactionUiState(categories = categoriesRepository.getAll())
    }

    @OptIn(ExperimentalUuidApi::class)
    suspend fun saveTransaction() {
        if (!validateInput()) return

        val id = Uuid.random().toString()
        val newTransaction =
            transactionUiState.value.transactionDetails.toTransaction(id)

        transactionsRepository.create(newTransaction)
    }

    private fun validateInput(
        uiState: TransactionDetails = transactionUiState.value.transactionDetails
    ): Boolean {
        return true
    }
}

data class TransactionUiState(
    val currentStep: Int = 0,
    val transactionDetails: TransactionDetails = TransactionDetails(),
    val categories: List<Category> = emptyList()
)

data class TransactionDetails(
    val type: TransactionType = TransactionType.INCOME,
    val cents: Long = 0,
    val date: Date = Date(),
    val description: String = "",
    val category: Category = Category.DEFAULT_CATEGORY
)

fun TransactionDetails.toTransaction(id: String): Transaction = Transaction(
    id = id,
    type = type,
    cents = cents,
    date = date,
    categoryId = category.id,
    description = description,
)