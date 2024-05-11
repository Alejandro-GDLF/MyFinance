package com.example.myfinance.transaction.data.mapper

import com.example.myfinance.core.Mapper
import com.example.myfinance.core.currency.CurrencyAmount
import com.example.myfinance.transaction.data.dao.TransactionDao
import com.example.myfinance.transaction.data.dao.TransactionTypeDao
import com.example.myfinance.transaction.data.entity.TransactionDbDto
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
): Mapper<TransactionDbDto, Transaction> {
    override fun from(item: Transaction): TransactionDbDto {
        return TransactionDbDto(
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

    override fun to(item: TransactionDbDto): Transaction {
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