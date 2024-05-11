package com.example.myfinance.transaction.presentation.list

import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TransactionListViewModel @Inject constructor(
    val dateTimeFormatter: DateTimeFormatter,
    val currencyAmountFormatter: NumberFormat
) {
}