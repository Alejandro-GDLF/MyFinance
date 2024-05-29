package com.example.myfinance.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.myfinance.account.domain.Account

@Composable
fun DropDownAccount(
    options: List<Account>,
    onSelectedAccount: (Account) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(-1) }
    
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        Column {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { expanded = true }) {
                Text(text = if (selectedOption == -1) "Select an Account" else options.get(selectedOption).number)
            }

            DropdownMenu(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEachIndexed { index, account ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { Text(text = account.number) },
                        onClick = {
                            selectedOption = index
                            expanded = false
                            onSelectedAccount(account)
                        }
                    )
                }
            }
        }
    }
}