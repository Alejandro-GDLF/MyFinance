package com.example.myfinance.home.presentation

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import java.time.format.DateTimeFormatter

data class HomeState(
    val accounts: List<Account> = listOf(),
    val dateFormatter: DateTimeFormatter,
    val currencyFormatter: CurrencyAmountFormatter,
    val isLoading: Boolean = false
)
