package com.example.myfinance.transaction.data.persistance.repository

import com.example.myfinance.account.domain.Account
import com.example.myfinance.transaction.data.persistance.dao.TransactionDao
import com.example.myfinance.transaction.data.persistance.mapper.RoomTransactionMapper
import com.example.myfinance.transaction.domain.model.Transaction
import com.example.myfinance.transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.stream.Collectors
import javax.inject.Inject

class RoomTransactionRepository @Inject constructor(
    val transactionDao: TransactionDao,
    val transactionTypeRepository: RoomTransactionTypeRepository,
    val mapper: RoomTransactionMapper
): TransactionRepository {

    override suspend fun read(): List<Transaction> = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }

    suspend fun getAllByAccount(id: Long): List<Transaction> = withContext(Dispatchers.IO) {
        val transactions = transactionDao.getAllByAccountId(id)

        return@withContext transactions.map { mapper.toDomain(it, transactionTypeRepository.get(it.typeId)) }
    }

    override suspend fun create(account: Account, transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }
}