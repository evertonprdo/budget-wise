package com.evertonprdo.budgetwise.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun FancyCurrencyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = remember { FocusRequester() },
    prefix: String = "$",
    separator: String = ".",
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .semantics(mergeDescendants = true) {
                contentDescription = "Currency Input"
            }
            .clickable(onClickLabel = "Fill the amount") {
                focusManager.clearFocus()
                focusRequester.requestFocus()
            }
    ) {
        val highlightedText = if (value.length > 2) {
            val splitIndex = value.length - 2

            HighlightedText(
                prefix = prefix,
                suffix = value.substring(splitIndex),
                text = value
                    .substring(0, splitIndex)
                    .formatStringWithSeparator(separator),
            )
        } else {
            HighlightedText(
                prefix = prefix,
                text = "0",
                suffix = value.padStart(2, '0')
            )
        }

        HighlightText(highlightedText)

        // Hidden field to handle user input
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it.getDigits()) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier
                .size(1.dp)
                .alpha(0f)
                .focusRequester(focusRequester)
        )
    }
}

@Composable
private fun HighlightText(
    highlightedText: HighlightedText,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        Text(
            text = highlightedText.prefix,
            style = MaterialTheme.typography.headlineLarge,
            softWrap = false,
            modifier = Modifier
                .paddingFromBaseline(top = 40.sp)

        )
        Text(
            text = highlightedText.text,
            style = MaterialTheme.typography.displayLarge,
            softWrap = false
        )

        Text(
            text = highlightedText.suffix,
            style = MaterialTheme.typography.headlineLarge,
            softWrap = false,

            modifier = Modifier
                .paddingFromBaseline(top = 40.sp)
        )
    }
}

private data class HighlightedText(
    val prefix: String = "",
    val text: String = "",
    val suffix: String = ""
)

private fun String.getDigits(): String =
    this.filter { it.isDigit() }

private fun String.formatStringWithSeparator(separator: String = "."): String {
    if (this.isEmpty()) return this

    val firstGroupSize = this.length % 3

    val firstGroup = this.take(firstGroupSize)
    val remainingGroups = this.drop(firstGroupSize).chunked(3)

    if (firstGroupSize == 0)
        return remainingGroups.joinToString(separator)

    return arrayOf(firstGroup)
        .plus(remainingGroups)
        .joinToString(separator)
}

@Preview
@Composable
private fun HighlightedInputPreview() {
    BudgetWiseTheme {
        FancyCurrencyTextField(
            value = "10000000",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}