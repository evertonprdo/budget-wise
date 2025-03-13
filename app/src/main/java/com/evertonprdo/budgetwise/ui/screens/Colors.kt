package com.evertonprdo.budgetwise.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ColorsTest(modifier: Modifier = Modifier) {
    val colors = mapOf(
        // error
        "error" to MaterialTheme.colorScheme.error,
        "errorContainer" to MaterialTheme.colorScheme.errorContainer,
        "onError" to MaterialTheme.colorScheme.onError,
        "onErrorContainer" to MaterialTheme.colorScheme.onErrorContainer,

        // surface
        "surface" to MaterialTheme.colorScheme.surface,
        "onSurface" to MaterialTheme.colorScheme.onSurface,

        "surfaceContainerLowest" to MaterialTheme.colorScheme.surfaceContainerLowest,
        "surfaceContainerLow" to MaterialTheme.colorScheme.surfaceContainerLow,
        "surfaceContainer" to MaterialTheme.colorScheme.surfaceContainer,
        "surfaceContainerHigh" to MaterialTheme.colorScheme.surfaceContainerHigh,
        "surfaceContainerHighest" to MaterialTheme.colorScheme.surfaceContainerHighest,

        "surfaceDim" to MaterialTheme.colorScheme.surfaceDim,
        "surfaceTint" to MaterialTheme.colorScheme.surfaceTint,
        "surfaceBright" to MaterialTheme.colorScheme.surfaceBright,

        "surfaceVariant" to MaterialTheme.colorScheme.surfaceVariant,
        "onSurfaceVariant" to MaterialTheme.colorScheme.onSurfaceVariant,

        "inverseSurface" to MaterialTheme.colorScheme.inverseSurface,
        "inverseOnSurface" to MaterialTheme.colorScheme.inverseOnSurface,

        // Primary
        "primary" to MaterialTheme.colorScheme.primary,
        "onPrimary" to MaterialTheme.colorScheme.onPrimary,

        "primaryContainer" to MaterialTheme.colorScheme.primaryContainer,
        "onPrimaryContainer" to MaterialTheme.colorScheme.onPrimaryContainer,

        "inversePrimary" to MaterialTheme.colorScheme.inversePrimary,

        // Secondary
        "secondary" to MaterialTheme.colorScheme.secondary,
        "onSecondary" to MaterialTheme.colorScheme.onSecondary,

        "secondaryContainer" to MaterialTheme.colorScheme.secondaryContainer,
        "onSecondaryContainer" to MaterialTheme.colorScheme.onSecondaryContainer,

        // Tertiary
        "tertiary" to MaterialTheme.colorScheme.tertiary,
        "onTertiary" to MaterialTheme.colorScheme.onTertiary,

        "tertiaryContainer" to MaterialTheme.colorScheme.tertiaryContainer,
        "onTertiaryContainer" to MaterialTheme.colorScheme.onTertiaryContainer,

        // background
        "background" to MaterialTheme.colorScheme.background,
        "onBackground" to MaterialTheme.colorScheme.onBackground,

        // outline
        "outline" to MaterialTheme.colorScheme.outline,
        "outlineVariant" to MaterialTheme.colorScheme.outlineVariant,

        // Scrim
        "scrim" to MaterialTheme.colorScheme.scrim,
    )

    LazyVerticalGrid(
        state = rememberLazyGridState(),
        columns = GridCells.Adaptive(300.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(colors.toList(), { (key, _) -> key }) { (key, color) ->
            Card {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                ) {
                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            text = "$key ${String.format("#%08X", color.toArgb())}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Sample text",
                            style = MaterialTheme.typography.headlineSmall,
                            color = color,
                        )
                    }
                    Spacer(
                        Modifier
                            .background(color, MaterialTheme.shapes.medium)
                            .size(48.dp)
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ColorsPreview() {
    BudgetWiseTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ColorsTest()
        }
    }
}