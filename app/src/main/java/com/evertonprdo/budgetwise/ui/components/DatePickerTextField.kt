package com.evertonprdo.budgetwise.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTextField(
    datePicker: DatePickerState,
    displayedDate: String,
    modifier: Modifier = Modifier,
) {
    var openDialog by rememberSaveable { mutableStateOf(false) }

    TextFieldBox(
        value = displayedDate,
        onClick = { openDialog = true },
        modifier = modifier,
        trailingIcon = {
            Icon(Icons.Default.DateRange, null)
        }
    )

    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                Button({ openDialog = false }) {
                    Text(
                        text = "ok",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        ) {
            DatePicker(state = datePicker)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DatePickerPreview() {
    BudgetWiseTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(1.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            DatePickerTextField(
                rememberDatePickerState(),
                "mm/dd/yyyy"
            )
        }
    }
}


