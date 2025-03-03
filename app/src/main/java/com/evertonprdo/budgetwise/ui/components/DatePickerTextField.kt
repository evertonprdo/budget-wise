package com.evertonprdo.budgetwise.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .clickable { openDialog = true }
            .background(
                MaterialTheme.colorScheme.surfaceContainerHighest,
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                )
            )
            .indicatorLine(
                enabled = true,
                isError = false,
                interactionSource = remember { MutableInteractionSource() },
                colors = TextFieldDefaults.colors(),
            )
            .padding(horizontal = 16.dp)
            .height(56.dp)
    ) {
        Text(
            text = displayedDate,
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(Icons.Default.DateRange, null)
    }

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


