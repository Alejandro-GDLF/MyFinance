package com.example.myfinance.account.domain

import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.domain.model.Transaction
import java.time.LocalDateTime

data class Account (
    var id: Long?,
    var number: String,
    var balance: CurrencyAmount,
    var creationDate: LocalDateTime,
    val transactions: List<Transaction> = mutableListOf()
) {
    fun createTransaction(transaction: Transaction) {

    }
}