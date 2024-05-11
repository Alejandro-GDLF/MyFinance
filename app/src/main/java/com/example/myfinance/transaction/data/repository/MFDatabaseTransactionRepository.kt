package com.example.myfinance.transaction.data.repository

import com.example.myfinance.core.Mapper
import com.example.myfinance.transaction.data.dao.TransactionDao
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.stream.Collectors
import javax.inject.Inject

class MFDatabaseTransactionRepository @Inject constructor(
    val transactionDao: TransactionDao,
    val mapper: Mapper<com.example.myfinance.transaction.data.entity.TransactionDbDto, Transaction>
): TransactionRepository {

    override suspend fun read(): List<Transaction> = withContext(Dispatchers.IO) {
        return@withContext transactionDao.readAll().stream()
            .map { mapper.to(it) }
            .collect(Collectors.toList())
    }
}