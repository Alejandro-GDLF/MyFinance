package com.example.myfinance.transactions.presentation

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import java.time.format.DateTimeFormatter


data class TransactionsState (
    var account: Account,
    val dateFormatter: DateTimeFormatter,
    val currencyFormatter: CurrencyAmountFormatter,
    var isLoading: Boolean = false,
    var errorMessage: String? = null
)