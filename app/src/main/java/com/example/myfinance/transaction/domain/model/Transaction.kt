package com.example.myfinance.transaction.domain.model


import com.example.myfinance.core.currency.CurrencyAmount
import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val type: TransactionType,
    val amount: CurrencyAmount,
    val date: LocalDateTime,
    val description: String,
    val total: CurrencyAmount
)
