package com.example.myfinance.transaction.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transactions.presentation.TransactionKeySelector

data class TransactionSelectorDropDownItem(
    val name: String,
    val selector: TransactionKeySelector
)

@Composable
fun TransactionSelectorDropDown(
    options: List<TransactionSelectorDropDownItem>,
    onSelect: (TransactionKeySelector) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }

    onSelect(options.get(selectedOption).selector)

    Column {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { expanded = true }
        ) {
            Text(
                text = "Group by: ${options.get(selectedOption).name}"
            )
        }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, groupBySelector ->
                DropdownMenuItem(
                    text = { Text(text = groupBySelector.name) },
                    onClick = {
                        selectedOption = index
                        expanded = false
                        onSelect(groupBySelector.selector)
                    }
                )
            }
        }
    }
}