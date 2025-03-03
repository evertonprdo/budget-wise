package com.evertonprdo.budgetwise.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.evertonprdo.budgetwise.ui.components.FancyCurrencyTextField
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun InsertScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        var text by remember { mutableStateOf("") }

        FancyCurrencyTextField(
            text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun InsertScreenPreview() {
    BudgetWiseTheme { InsertScreen() }
}
