package com.evertonprdo.budgetwise.ui.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.budgetwise.R
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun FancyCurrencyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = remember { FocusRequester() },
    prefix: String = stringResource(R.string.currency_symbol),
    separator: String = stringResource(R.string.decimal_separator),
    maxLength: Int = 20,
    scrollState: ScrollState = rememberScrollState()
) {
    LaunchedEffect(value) {
        scrollState.animateScrollBy(
            scrollState.viewportSize.toFloat(),
            animationSpec = TweenSpec(300)
        )
    }

    val currencyInput = stringResource(R.string.currency_input)
    val currencyInputOnClickLabel = stringResource(R.string.currency_input_on_click_label)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .semantics(mergeDescendants = true) {
                contentDescription = currencyInput
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClickLabel = currencyInputOnClickLabel
            ) {
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

        HighlightText(
            highlightedText = highlightedText,
            scrollState = scrollState
        )

        // Hidden field to handle user input
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.length < maxLength)
                    onValueChange(it.getDigits())
            },
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
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .width(IntrinsicSize.Max)
            .horizontalScroll(scrollState)
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

private fun String.formatStringWithSeparator(separator: String): String {
    if (this.isEmpty()) return this

    val firstGroupSize = this.length % 3
    val remainingGroups = this.drop(firstGroupSize).chunked(3)

    if (firstGroupSize == 0)
        return remainingGroups.joinToString(separator)

    return arrayOf(this.take(firstGroupSize))
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