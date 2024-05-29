package com.example.myfinance.transaction.data.persistance.mapper

import androidx.collection.arrayMapOf
import com.example.myfinance.account.domain.Account
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.model.TransactionType
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.stream.Collectors

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

    fun toPersistance(transaction: Transaction, account: Account): TransactionEntity {
        return TransactionEntity(
            id = transaction.id ?: 0L,
            typeId = transaction.type.id!!,
            accountId = account.id!!,
            amount = transaction.amount.amount.toLong(),
            currencyCode = transaction.amount.currency.currencyCode,
            date = transaction.date.toEpochSecond(ZoneOffset.UTC),
            description = transaction.description,
            total = account.transactions.sumOf { it.amount.amount.toLong() } + transaction.amount.amount.toLong()
        )
    }
}