package com.evertonprdo.budgetwise.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun HighlightedInput(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    highlightedText: HighlightedText = HighlightedText(),
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done,
    ),
    keyboardActions: KeyboardActions = KeyboardActions(
        onDone = { focusManager.clearFocus() }
    )
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable {
                focusManager.clearFocus()
                focusRequester.requestFocus()
            }
    ) {
        HighlightText(highlightedText)

        // Hidden field to handle user input, it seems like it's possible
        // to build a class to do this with PlatformTextInputModifierNode
        BasicTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle.Default,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
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
            style = MaterialTheme.typography.displaySmall,
            softWrap = false,
            modifier = Modifier
                .paddingFromBaseline(top = 44.sp)

        )
        Text(
            text = highlightedText.text,
            style = MaterialTheme.typography.displayLarge,
            softWrap = false
        )

        Text(
            text = highlightedText.suffix,
            style = MaterialTheme.typography.displaySmall,
            softWrap = false,

            modifier = Modifier
                .paddingFromBaseline(top = 44.sp)
        )
    }
}

data class HighlightedText(
    val prefix: String = "",
    val text: String = "",
    val suffix: String = ""
)

@Preview
@Composable
private fun HighlightedInputPreview() {
    BudgetWiseTheme {
        HighlightedInput(
            textFieldValue = TextFieldValue("10000000"),
            onValueChange = {},
            highlightedText = HighlightedText(
                prefix = "$",
                text = "100.000",
                suffix = "00",
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}