package com.evertonprdo.budgetwise.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.R

@Composable
fun TextFieldBox(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable() () -> Unit = {},
    trailingIcon: @Composable() () -> Unit = {},
) {
    CoreTextFieldBox(
        onClick = onClick,
        modifier = modifier
    ) {
        leadingIcon()

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        trailingIcon()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreTextFieldBox(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    enabled: Boolean = true,
    content: @Composable (RowScope.() -> Unit)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                MaterialTheme.colorScheme.surfaceContainerHighest,
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                )
            )
            .indicatorLine(
                enabled = enabled,
                isError = isError,
                interactionSource = remember { MutableInteractionSource() },
                colors = TextFieldDefaults.colors(),
            )
            .padding(horizontal = 16.dp)
            .height(dimensionResource(R.dimen.text_field_height))
    ) { content() }
}