package com.evertonprdo.budgetwise.ui.screens.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.budgetwise.R
import com.evertonprdo.budgetwise.model.Category
import com.evertonprdo.budgetwise.ui.AppViewModelProvider
import com.evertonprdo.budgetwise.ui.components.DatePickerTextField
import com.evertonprdo.budgetwise.ui.components.FancyCurrencyTextField
import com.evertonprdo.budgetwise.ui.components.SelectCategoryTextField
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(viewModel: EntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState by viewModel.transactionUiState.collectAsState()
    val currentStep = 1

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 40.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Title",
            style = MaterialTheme.typography.displaySmall
        )

        when (currentStep) {
            0 -> {
                Column {
                    InputWrapper("Title") {
                        FancyCurrencyTextField("", {})
                    }
                }
            }

            1 -> {
                val textFieldHeight =
                    dimensionResource(R.dimen.text_field_height)
                val selectWrapperHeight = textFieldHeight + 40.dp

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(top = selectWrapperHeight + 16.dp)
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
                            categories = uiState.categories,
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
            }
        }
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
