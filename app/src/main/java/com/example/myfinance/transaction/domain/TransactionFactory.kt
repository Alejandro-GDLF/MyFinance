package com.example.myfinance.transaction.domain

import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.inject.Inject

class TransactionFactory @Inject constructor() {
    fun create(
        description: String,
        date: LocalDateTime,
        amount: CurrencyAmount,
        type: TransactionType
    ): Transaction {
        return Transaction(
            id = null,
            description = description,
            date = date,
            amount = amount,
            type = type,
            total = CurrencyAmount(BigDecimal(0), amount.currency)
        )
    }
}