package com.example.myfinance.home.presentation

import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmountFormatter
import java.time.format.DateTimeFormatter

data class HomeState(
    var account: Account?,
    var dateFormatter: DateTimeFormatter,
    var currencyFormatter: CurrencyAmountFormatter
)
