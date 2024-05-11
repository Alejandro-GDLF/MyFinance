package com.example.myfinance.core.currency

import java.math.BigDecimal
import java.util.Currency

data class CurrencyAmount(
    val amount: BigDecimal,
    val currency: Currency
)
