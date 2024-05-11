package com.example.myfinance.core.currency

import java.text.NumberFormat

class CurrencyAmountFormatter {
    fun format(currencyAmount: CurrencyAmount): String  {
        val format = NumberFormat.getCurrencyInstance()
        format.currency = currencyAmount.currency
        return format.format(currencyAmount.amount)
    }
}