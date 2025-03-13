package com.evertonprdo.budgetwise.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.evertonprdo.budgetwise.ui.screens.ColorsTest
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetWiseApp() {
    val focusManager = LocalFocusManager.current

    BudgetWiseTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Screen Title") }) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = { focusManager.clearFocus() })
                    }
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ColorsTest()
            }
        }
    }
}