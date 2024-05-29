package com.example.myfinance.transaction.presentation.new_transaction_type

data class NewTransactionTypeState(
    val description: String = "",
    val isLoading: Boolean = false,
    val isCreated: Boolean = false
)