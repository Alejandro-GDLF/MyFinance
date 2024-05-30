package com.example.myfinance.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfinance.account.domain.Account

@Composable
fun DropDownAccount(
    options: List<Account>,
    defaultAccount: Account?,
    onAccountSelected: (Account) -> Unit
) {
    // Estado para controlar si el menú está expandido o no
    var expanded by remember { mutableStateOf(false) }
    // Estado para almacenar la opción seleccionada, inicializado con el valor por defecto
    var selectedOption by remember { mutableStateOf(defaultAccount) }

    // Efecto lanzado cuando el valor por defecto cambia, para actualizar el estado de la opción seleccionada
    LaunchedEffect(defaultAccount) {
        selectedOption = defaultAccount
    }

    Column {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { if (options.isNotEmpty()) expanded = true }
        ) {
            Text(text = when {
                options.isEmpty() -> "No accounts available"
                selectedOption != null -> selectedOption!!.number
                else -> "Select an account"
            })
        }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { account ->
                DropdownMenuItem(
                    text = { Text(text = account.number) },
                    onClick = {
                        selectedOption = account
                        expanded = false
                        onAccountSelected(account)
                    }
                )
            }
        }
    }
}
