package com.evertonprdo.budgetwise.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evertonprdo.budgetwise.ui.screens.InsertScreen
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun BudgetWiseApp() {
    BudgetWiseTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            InsertScreen()
        }
    }
}