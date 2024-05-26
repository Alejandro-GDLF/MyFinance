package com.example.myfinance.transaction.data.persistance.mapper

import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import java.time.LocalDateTime
import java.time.ZoneOffset

object RoomTransactionMapper {
    fun toDomain(transaction: TransactionEntity, transactionType: TransactionType) = Transaction(
        id  = transaction.id,
        type = transactionType,
        amount = CurrencyAmount(
            transaction.amount,
            transaction.currencyCode
        ),
        date = LocalDateTime.ofEpochSecond(transaction.date, 0, ZoneOffset.UTC),
        description = transaction.description,
        total = CurrencyAmount(
            transaction.total,
            transaction.currencyCode
        )
    )
}