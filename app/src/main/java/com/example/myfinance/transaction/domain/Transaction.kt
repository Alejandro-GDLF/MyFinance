package com.example.myfinance.transaction.domain

import android.icu.util.CurrencyAmount
import android.icu.util.LocaleData
import com.example.myfinance.account.data.entity.Account
import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val type: TransactionType,
    val amount: CurrencyAmount,
    val date: LocalDateTime,
    val description: String,
    val total: CurrencyAmount
)
