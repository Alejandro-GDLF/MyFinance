package com.example.myfinance.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AddTransactionFloatingButton(
    navController: NavHostController
) {
    FloatingActionButton(onClick = { navController.navigate("create_transaction") }) {
        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = "Add new transaction"
        )
    }
}