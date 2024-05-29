package com.example.myfinance.account.presentation.new_account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun NewAccountScreen(
    state: NewAccountState,
    createAccount: (NewAccountState) -> Unit,
    navHostController: NavHostController,
    updateNumber: (String)-> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create new account",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = state.number,
            onValueChange = updateNumber,
            placeholder = { Text(text = "Number") }
        )

        Button(
            onClick = { createAccount(state).also {
                navHostController.popBackStack()
            } }
        ) {
            Text(text = "Create account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewAccountScreenPreview() {
    NewAccountScreen(NewAccountState(), {}, rememberNavController(), {})
}