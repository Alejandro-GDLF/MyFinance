package com.example.myfinance.transaction.presentation.new_transaction_type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun NewTransactionType(
    state: NewTransactionTypeState,
    navController: NavHostController,
    updateDescription: (String) -> Unit,
    onCreate: () -> Unit
) {
    if( state.isCreated ) {
        navController.popBackStack("create_transaction", false)
        return
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create new transaction type", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = state.description,
            onValueChange = updateDescription
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onCreate ) {
            Text(text = "Create type")
        }
    }
}