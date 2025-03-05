package com.evertonprdo.budgetwise.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.model.Category

@Composable
fun SelectCategoryTextField(
    selected: Category,
    categories: List<Category>,
    expanded: Boolean,
    onClick: () -> Unit,
    onSelect: (Category) -> Unit,
    modifier: Modifier = Modifier
) {

    val density = LocalDensity.current

    var boxWidth by remember { mutableIntStateOf(0) }
    val boxWidthDp = remember(boxWidth) {
        with(density) { boxWidth.toDp() }
    }

    Box(modifier.onSizeChanged { size -> boxWidth = size.width }) {

        SelectTextField(
            focused = expanded,
            onClick = onClick
        ) {
            CategoryTag(
                category = selected,
                modifier = Modifier.weight(1f)
            )
        }

        SelectOptions(
            expanded = expanded,
            onDismissRequest = onClick,
            modifier = Modifier.width(boxWidthDp)
        ) {
            for (category in categories) {
                CategoryTag(
                    category = category,
                    modifier = modifier
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onSelect(category) }
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SelectTextField(
    focused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: @Composable (RowScope.() -> Unit),
) {
    val imageVector = if (focused)
        Icons.Default.KeyboardArrowDown
    else
        Icons.Default.KeyboardArrowUp

    CoreTextFieldBox(
        onClick = onClick,
        modifier = modifier
    ) {
        selected()

        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Composable
fun SelectOptions(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    maxHeight: Dp = 300.dp,
    content: @Composable () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        shape = MaterialTheme.shapes.large.copy(
            topStart = CornerSize(0),
            topEnd = CornerSize(0)
        ),
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .heightIn(max = maxHeight)
            .padding(8.dp)
    ) {
        content()
    }
}

