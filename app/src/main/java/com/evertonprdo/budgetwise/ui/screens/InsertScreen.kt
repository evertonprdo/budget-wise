package com.evertonprdo.budgetwise.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.ui.components.HighlightedInput
import com.evertonprdo.budgetwise.ui.components.HighlightedText
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun InsertScreen(modifier: Modifier = Modifier) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    val highlightedText by remember {
        derivedStateOf {
            val prefix = "$"
            if (textFieldValue.text.length > 2) {
                val splitIndex = textFieldValue.text.length - 2

                HighlightedText(
                    prefix = prefix,
                    suffix = textFieldValue.text.substring(splitIndex),
                    text = textFieldValue.text
                        .substring(0, splitIndex)
                        .formatStringWithSeparator(),
                )
            } else {
                HighlightedText(
                    prefix = prefix,
                    text = "0",
                    suffix = textFieldValue.text
                )
            }
        }
    }

    Surface(modifier) {
        HighlightedInput(
            textFieldValue = textFieldValue,
            highlightedText = highlightedText,
            onValueChange = {
                val newValue = it.text.getDigits()
                textFieldValue = it.copy(
                    text = newValue,
                    selection = TextRange(newValue.length)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun InsertScreenPreview() {
    BudgetWiseTheme { InsertScreen() }
}

private fun String.getDigits(): String =
    this.filter { it.isDigit() }

private fun String.formatStringWithSeparator(separator: String = "."): String {
    if (this.isEmpty()) return ""

    val firstGroupSize = this.length % 3
    val firstGroup = if (firstGroupSize != 0) this.take(firstGroupSize) else ""
    val remainingGroups = this.drop(firstGroupSize).chunked(3)

    return listOfNotNull(firstGroup.takeIf { it.isNotEmpty() })
        .plus(remainingGroups)
        .joinToString(separator)
}