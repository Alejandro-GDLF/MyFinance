package com.example.myfinance.transaction.data.persistance.repository

import com.example.myfinance.transaction.data.persistance.dao.TransactionTypeDao
import com.example.myfinance.transaction.data.persistance.mapper.RoomTransactionTypeMapper
import com.example.myfinance.transaction.domain.model.TransactionType
import com.example.myfinance.transaction.domain.repository.TransactionRepository
import com.example.myfinance.transaction.domain.repository.TransactionTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomTransactionTypeRepository @Inject constructor (
    val transactionTypeDao: TransactionTypeDao,
    val mapper: RoomTransactionTypeMapper
): TransactionTypeRepository {
    override suspend fun get(id: Long) = withContext(Dispatchers.IO) {
        val type = transactionTypeDao.get(id)
        return@withContext mapper.toDomain(type)
    }

    override suspend fun getAll(): List<TransactionType> = withContext(Dispatchers.IO){
        val types = transactionTypeDao.getAll()

        return@withContext types.map { mapper.toDomain(it) }
    }

    override suspend fun save(transactionType: TransactionType): Long = withContext(Dispatchers.IO) {
        val type = mapper.toPersistance(transactionType)

        return@withContext transactionTypeDao.insert(type)
    }
}