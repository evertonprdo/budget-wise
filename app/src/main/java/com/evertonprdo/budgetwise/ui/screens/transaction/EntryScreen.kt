package com.evertonprdo.budgetwise.ui.screens.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.budgetwise.model.Category
import com.evertonprdo.budgetwise.ui.AppViewModelProvider
import com.evertonprdo.budgetwise.ui.components.DatePickerTextField
import com.evertonprdo.budgetwise.ui.components.FancyCurrencyTextField
import com.evertonprdo.budgetwise.ui.components.SelectCategoryTextField
import com.evertonprdo.budgetwise.ui.components.TextFieldBox
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

enum class FormStep {
    Amount,
    Details
}

@Composable
fun EntryScreen(viewModel: EntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState by viewModel.transactionUiState.collectAsState()
    var currentStep by remember { mutableStateOf(FormStep.Amount) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when (currentStep) {
            FormStep.Amount -> {
                FormAmountStep()
            }

            FormStep.Details -> {
                FormDetailsStep(uiState.categories)
            }
        }

        Spacer(Modifier.weight(1f))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { currentStep = FormStep.Amount },
                modifier = Modifier.weight(1f)
            ) { Text("Amount") }

            Button(
                onClick = { currentStep = FormStep.Details },
                modifier = Modifier.weight(1f)
            ) { Text("Details") }
        }
    }
}

@Composable
fun FormAmountStep(
    modifier: Modifier = Modifier
) {
    InputWrapper("Currency") {
        FancyCurrencyTextField("", {}, Modifier.fillMaxWidth())
    }

    InputWrapper("Type") {
        Row {
            Icon(Icons.Default.KeyboardArrowUp, null)
            TextFieldBox("Income", {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDetailsStep(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    var showOptions by remember { mutableStateOf(false) }
    var selectedCategory by remember {
        mutableStateOf(
            Category.DEFAULT_CATEGORY
        )
    }

    InputWrapper(title = "Category") {
        SelectCategoryTextField(
            selected = selectedCategory,
            categories = categories,
            expanded = showOptions,
            onClick = { showOptions = !showOptions },
            onSelect = {
                showOptions = false
                selectedCategory = it
            })
    }

    InputWrapper(title = "Date") {
        DatePickerTextField(
            datePicker = rememberDatePickerState(),
            displayedDate = "TO/DO/2025",
            modifier = Modifier.fillMaxWidth()
        )
    }

    InputWrapper(title = "Description") {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun InputWrapper(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        content()
    }
}

@Preview
@Composable
private fun InsertScreenPreview() {
    BudgetWiseTheme { EntryScreen() }
}
