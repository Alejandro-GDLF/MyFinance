package com.example.myfinance.transaction.domain.repository

import com.example.myfinance.transaction.domain.model.TransactionType

interface TransactionTypeRepository {
    suspend fun getAll(): List<TransactionType>
    suspend fun get(id: Long): TransactionType
}