package com.example.myfinance.transactions.presentation

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import java.time.format.DateTimeFormatter


data class TransactionsState (
    val accounts: List<Account> = listOf(),
    val dateFormatter: DateTimeFormatter,
    val currencyFormatter: CurrencyAmountFormatter,
    val isLoading: Boolean = false,
    val selectedAccount: Account? = null,
    var errorMessage: String? = null
)