package com.example.myfinance.stats.presentation

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import java.time.format.DateTimeFormatter

data class StatsScreenState (
    val isLoading: Boolean = true,
    val account: Account? = null,
    val balance: String = "",
    val spent: String = "",
    val income: String = "",
    val typeAndTotal: Map<String, String> = mapOf(),
    val dateTimeFormatter: DateTimeFormatter,
    val currencyAmountFormatter: CurrencyAmountFormatter
)