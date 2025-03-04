package com.evertonprdo.budgetwise.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.evertonprdo.budgetwise.data.local.LocalCategoriesRepository
import com.evertonprdo.budgetwise.model.Category
import com.evertonprdo.budgetwise.ui.theme.BudgetWiseTheme

@Composable
fun SelectCategoryTextField(
    onFocus: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Category = Category.DEFAULT_CATEGORY,
) {
    val imageVector = if (onFocus)
        Icons.Default.KeyboardArrowDown
    else
        Icons.Default.KeyboardArrowUp

    CoreTextFieldBox(
        onClick = onClick,
        modifier = modifier
    ) {
        CategoryTag(
            category = selected,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Composable
fun SelectCategoryOptions(
    categories: List<Category>,
    onSelect: (Category) -> Unit,
    shownOptions: Boolean,
    modifier: Modifier = Modifier,
    maxHeight: Dp = 300.dp
) {
    AnimatedVisibility(
        visible = shownOptions,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Surface(
            shape = MaterialTheme.shapes.large.copy(
                topStart = CornerSize(0),
                topEnd = CornerSize(0)
            ),
            color = MaterialTheme.colorScheme.surfaceContainerHighest,
            shadowElevation = 3.dp,
            tonalElevation = 3.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .heightIn(max = maxHeight)
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
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
}

@Preview
@Composable
private fun SelectTextFieldPreview() {
    BudgetWiseTheme {
        SelectCategoryTextField(true, {})
    }
}

@Preview
@Composable
private fun SelectOptionsPreview() {
    BudgetWiseTheme {
        SelectCategoryOptions(
            LocalCategoriesRepository.categories,
            {},
            true
        )
    }
}