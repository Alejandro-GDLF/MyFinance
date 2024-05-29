package com.example.myfinance.transaction.presentation.new_transaction

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.domain.model.TransactionType
import java.time.LocalDateTime

data class NewTransactionState (
    val type: TransactionType? = null,
    val amount: String? = null,
    val date: LocalDateTime? = null,
    val description: String? = null,

    val accounts: List<Account> = listOf(),
    val transactionType: List<TransactionType> = listOf(),
    val isLoading: Boolean = true,
    val selectedAccount: Account? = null,
    val isCreated: Boolean = false
)