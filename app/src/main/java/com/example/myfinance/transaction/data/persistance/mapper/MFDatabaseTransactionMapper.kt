package com.example.myfinance.transaction.data.persistance.mapper

import com.example.myfinance.core.Mapper
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.data.persistance.dao.TransactionDao
import com.example.myfinance.transaction.data.persistance.dao.TransactionTypeDao
import com.example.myfinance.transaction.data.persistance.entity.TransactionEntity
import com.example.myfinance.transaction.domain.model.Transaction
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Currency
import javax.inject.Inject

class MFDatabaseTransactionMapper @Inject constructor(
    val transactionDao: TransactionDao,
    val transactionTypeDao: TransactionTypeDao,
    val transactionTypeMapper: MFDbTransactionTypeMapper
): Mapper<TransactionEntity, Transaction> {
    override fun from(item: Transaction): TransactionEntity {
        return TransactionEntity(
            id = item.id,
            typeId = item.type.id,
            accountId = transactionDao.getAccountId(item.id),
            amount = item.amount.amount.toLong(),
            total = item.total.amount.toLong(),
            currencyCode = item.amount.currency.currencyCode,
            date = item.date.toEpochSecond(ZoneOffset.UTC),
            description = item.description
        )
    }

    override fun to(item: TransactionEntity): Transaction {
        return Transaction(
            id = item.id,
            type = transactionTypeMapper.to(transactionTypeDao.get(item.id)),
            amount = CurrencyAmount(item.amount.toBigDecimal(), Currency.getInstance(item.currencyCode)),
            date = LocalDateTime.ofInstant(Instant.ofEpochSecond(item.date), ZoneId.systemDefault()),
            description = item.description,
            total = CurrencyAmount(item.amount.toBigDecimal(), Currency.getInstance(item.currencyCode))
        )
    }
}