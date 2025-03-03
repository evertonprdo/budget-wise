package com.evertonprdo.budgetwise.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.ui.components.DatePickerTextField
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme
import java.text.DateFormat
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(modifier: Modifier = Modifier) {
    val datePicker = rememberDatePickerState()
    val date = remember(datePicker.selectedDateMillis) {

        val timestamp = datePicker.selectedDateMillis?.let { millis ->
            millis - defaultSystemOffset
        } ?: System.currentTimeMillis()

        DateFormat
            .getDateInstance(DateFormat.SHORT)
            .format(Date(timestamp))
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DatePickerTextField(datePicker, date)
    }
}

@Preview
@Composable
private fun InsertScreenPreview() {
    BudgetWiseTheme { InsertScreen() }
}

private val defaultSystemOffset =
    TimeZone.getDefault().getOffset(System.currentTimeMillis()).toLong()