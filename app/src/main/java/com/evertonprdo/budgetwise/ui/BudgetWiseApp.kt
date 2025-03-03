package com.evertonprdo.budgetwise.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.evertonprdo.budgetwise.ui.screens.InsertScreen
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun BudgetWiseApp() {
    val focusManager = LocalFocusManager.current

    BudgetWiseTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
        ) {
            InsertScreen()
        }
    }
}