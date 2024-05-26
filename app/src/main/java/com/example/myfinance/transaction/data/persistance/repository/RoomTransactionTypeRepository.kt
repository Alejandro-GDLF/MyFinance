package com.example.myfinance.transaction.data.persistance.repository

import com.example.myfinance.transaction.data.persistance.dao.TransactionTypeDao
import com.example.myfinance.transaction.data.persistance.mapper.RoomTransactionTypeMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomTransactionTypeRepository @Inject constructor (
    val transactionTypeDao: TransactionTypeDao,
    val mapper: RoomTransactionTypeMapper
) {
    suspend fun get(id: Long) = withContext(Dispatchers.IO) {
        val type = transactionTypeDao.get(id)
        return@withContext mapper.toDomain(type)
    }
}